package com.OnlineRadio.OnlineRadioStation.facade;

import com.OnlineRadio.OnlineRadioStation.models.*;
import com.OnlineRadio.OnlineRadioStation.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import com.OnlineRadio.OnlineRadioStation.WebSocket.RadioWebSocketHandler;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import com.OnlineRadio.OnlineRadioStation.iterators.Iterator;
import com.OnlineRadio.OnlineRadioStation.iterators.RandomSongIterator;
import com.OnlineRadio.OnlineRadioStation.iterators.SongIterator;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import jakarta.annotation.PostConstruct;

@Component
public class OnlineRadioFacade {

    private final UserService userService;
    private final PlaylistService playlistService;
    private final SongService songService;
    private final StreamingService streamingService;
    private final ArtistService artistService;

    private final RadioWebSocketHandler webSocketHandler;

    public OnlineRadioFacade(UserService userService, PlaylistService playlistService,
                             SongService songService, StreamingService streamingService,
                             ArtistService artistService, RadioWebSocketHandler webSocketHandler) {
        this.userService = userService;
        this.playlistService = playlistService;
        this.songService = songService;
        this.streamingService = streamingService;
        this.artistService = artistService;
        this.webSocketHandler = webSocketHandler;
    }

    @PostConstruct
    public void init() {
        if (!songService.getAllSongs().isEmpty()) {
            playNextSong();
        }
        List<User> existingAdmins = userService.searchUsersByLogin("Admin");
        if (existingAdmins.isEmpty()) {
            User adminUser = new User("Admin", "12345", User.Role.ADMIN);
            addOrUpdateUser(adminUser);
        }
    }

    public Artist getArtistById(Long id) {
        return artistService.getArtistById(id);
    }

    public List<Artist> searchArtistsByName(String name) {
        return artistService.searchArtistsByName(name);
    }

    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    public List<Song> getAllSongsByArtist(Long artistId) {
        return artistService.getAllSongsByArtist(artistId);
    }

    public Artist addOrUpdateArtist(Artist artist) {
        return artistService.addArtist(artist);
    }

    public void deleteArtist(Long artistId) {
        artistService.deleteArtist(artistId);
    }

    public void addSongToArtist(Long artistId, Long songId) {
        artistService.addSongToArtist(artistId, songId);
    }

    public void removeSongFromArtist(Long artistId, Long songId) {
        artistService.removeSongFromArtist(artistId, songId);
    }

    public Playlist getPlaylistById(Long id) {
        return playlistService.getPlaylistById(id);
    }

    public List<Playlist> searchPlaylistsByName(String name) {
        return playlistService.searchPlaylistsByName(name);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    public List<Song> getAllSongsInPlaylist(Long playlistId) {
        return playlistService.getAllSongsInPlaylist(playlistId);
    }

    public Playlist addOrUpdatePlaylist(Playlist playlist) {
        return playlistService.addPlaylist(playlist);
    }

    public void deletePlaylist(Long playlistId) {
        playlistService.deletePlaylist(playlistId);
    }

    public void addSongToPlaylist(Long playlistId, Long songId) {
        playlistService.addSongToPlaylist(playlistId, songId);
    }

    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        playlistService.removeSongFromPlaylist(playlistId, songId);
    }

    public Song getSongById(Long id) {
        return songService.getSongById(id);
    }

    public List<Song> searchSongsByTitle(String title) {
        return songService.searchSongsByTitle(title);
    }

    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    public List<Artist> getAllArtistsForSong(Long songId) {
        return songService.getAllArtistsForSong(songId);
    }

    public Song addOrUpdateSong(Song song) {
        return songService.addSong(song);
    }

    public void deleteSong(Long songId) {
        songService.deleteSong(songId);
    }

    public void addArtistToSong(Long songId, Long artistId) {
        songService.addArtistToSong(songId, artistId);
    }

    public void removeArtistFromSong(Long songId, Long artistId) {
        songService.removeArtistFromSong(songId, artistId);
    }

    public void addToQueue(Long songId) {
        try {
            Song song = songService.getSongById(songId);
            if (song == null) {
                throw new IllegalArgumentException("Song with ID " + songId + " not found!");
            }
            streamingService.addToQueue(song);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public Queue<Song> getQueue(){
        return streamingService.getQueue();
    }

    public void addPlaylistToQueue(Long playlistId, boolean shuffle) {
        List<Song> songs = getAllSongsInPlaylist(playlistId);
        Iterator<Song> iterator = shuffle ? new RandomSongIterator(songs) : new SongIterator(songs);

        while (iterator.hasNext()) {
            addToQueue(iterator.next().getId());
        }
    }

    public void clearQueue() {
        streamingService.clearQueue();
    }

    public void shuffleQueue() {
        streamingService.shuffleQueue();
    }

    public Map<String, Song> getFormattedSongHistory() {
        Map<Long, Song> songHistory = streamingService.getSongHistory();
        Map<String, Song> formattedHistory = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Map.Entry<Long, Song> entry : songHistory.entrySet()) {
            long timestamp = entry.getKey();
            Song song = entry.getValue();
            Date date = new Date(timestamp);
            String formattedTime = sdf.format(date);
            formattedHistory.put(formattedTime, song);
        }
        return formattedHistory;
    }

    public ResponseEntity<StreamingResponseBody> steamSong(int bitrate) {
        try {
            return streamingService.playSong(bitrate);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public Map<String, Object> syncWithRadio() {
        Map<String, Object> response = new HashMap<>();
        response.put("songId", streamingService.getCurrentSong().getId());
        response.put("currentTime", streamingService.getCurrentTime());
        response.put("currentTitle", streamingService.getCurrentSong().getTitle());
        List<String> currentSongArtistNames = getAllArtistsForSong(streamingService.getCurrentSong().getId())
                .stream()
                .map(Artist::getName)
                .collect(Collectors.toList());
        response.put("currentSongArtists", currentSongArtistNames);

        return response;
    }

    public void playNextSong(){
        streamingService.startNextSong();
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    public List<User> searchUsersByLogin(String login) {
        return userService.searchUsersByLogin(login);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void registerUser(String username, String rawPassword) {
        userService.registerUser(username, rawPassword, User.Role.USER);
    }

    public User addOrUpdateUser(User user) {
        return userService.addUser(user);
    }

    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }

    public Map<String, Integer> getUserCountHistory(){
        return webSocketHandler.getUserCountHistory();
    }
}
