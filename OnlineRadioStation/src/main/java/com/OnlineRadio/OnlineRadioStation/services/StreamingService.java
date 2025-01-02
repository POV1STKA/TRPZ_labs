package com.OnlineRadio.OnlineRadioStation.services;
import java.util.concurrent.ScheduledFuture;
import com.OnlineRadio.OnlineRadioStation.iterators.Iterator;
import com.OnlineRadio.OnlineRadioStation.iterators.RandomSongIterator;
import com.OnlineRadio.OnlineRadioStation.iterators.SongIterator;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.WebSocket.RadioWebSocketHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class StreamingService {
    private long startTime;
    private Queue<Song> songQueue = new LinkedList<>();
    private Iterator<Song> songIterator;
    private Song currentSong;
    private final SongService songService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> currentMonitoringTask;
    private final RadioWebSocketHandler radioWebSocketHandler;
    private Map<Long, Song> songHistory = new LinkedHashMap<>();


    public StreamingService(SongService songService, RadioWebSocketHandler radioWebSocketHandler) {
        this.songService = songService;
        this.radioWebSocketHandler = radioWebSocketHandler;
    }

    public void addToQueue(Song song) {
        songQueue.add(song);
    }

    public void clearQueue() {
        songQueue.clear();
        initializeIterator();
    }

    public void shuffleQueue() {
        List<Song> songList = new ArrayList<>(songQueue);
        Collections.shuffle(songList);
        songQueue.clear();
        songQueue.addAll(songList);
        initializeIterator();
    }

    private void initializeIterator() {
        if (songQueue.isEmpty()) {
            songIterator = new RandomSongIterator(songService.getAllSongs());
        } else {
            songIterator = new SongIterator(new ArrayList<>(songQueue));
        }
    }

    public ResponseEntity<StreamingResponseBody> playSong(int bitrate) throws IOException {
        if (currentSong == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if (!isValidBitrate(bitrate)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String adjustedFilePath = adjustFilePathForBitrate(currentSong.getFilePath(), bitrate);

        final InputStream audioStream;
        try {
            audioStream = new FileInputStream(adjustedFilePath);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        StreamingResponseBody responseBody = outputStream -> {
            try {
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = audioStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } finally {
                try {
                    audioStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"radio_" + bitrate + "kbps.mp3\"");

        return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
    }

    private boolean isValidBitrate(int bitrate) {
        return bitrate == 64 || bitrate == 92 || bitrate == 128 || bitrate == 196 || bitrate == 224;
    }

    private String adjustFilePathForBitrate(String originalFilePath, int bitrate) {
        int lastDotIndex = originalFilePath.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new IllegalArgumentException("Invalid file path: " + originalFilePath);
        }
        String baseName = originalFilePath.substring(0, lastDotIndex);
        String extension = originalFilePath.substring(lastDotIndex);
        return baseName + "_" + bitrate + "kbps" + extension;
    }

    public void startSongMonitoring(Song currentSong) {
        if (currentMonitoringTask != null && !currentMonitoringTask.isCancelled()) {
            currentMonitoringTask.cancel(true);
        }

        currentMonitoringTask = scheduler.scheduleAtFixedRate(() -> {
            long elapsedTime = getCurrentTime();

            if (elapsedTime >= currentSong.calculateDuration()) {
                startNextSong();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void startNextSong() {
        try {
            initializeIterator();
            songQueue.poll();
            this.currentSong = songIterator.next();
            startTime = System.currentTimeMillis();
            songHistory.put(startTime, currentSong);
            startSongMonitoring(this.currentSong);
            radioWebSocketHandler.sendPlayCommand();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getCurrentTime() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - startTime) / 1000;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public Queue<Song> getQueue(){
        return songQueue;
    }

    public Map<Long, Song> getSongHistory() {
        return songHistory;
    }
}
