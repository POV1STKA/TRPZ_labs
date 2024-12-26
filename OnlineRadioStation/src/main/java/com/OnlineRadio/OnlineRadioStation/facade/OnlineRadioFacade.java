package com.OnlineRadio.OnlineRadioStation.facade;

import com.OnlineRadio.OnlineRadioStation.models.*;
import com.OnlineRadio.OnlineRadioStation.services.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

@Service
public class OnlineRadioFacade {
    private final UserService userService;
    private final PlaylistService playlistService;
    private final SongService songService;
    private final StreamingService streamingService;
    private final ArtistService artistService;
    private final StatisticService statisticService;

    public OnlineRadioFacade(UserService userService, PlaylistService playlistService,
                             SongService songService, StreamingService streamingService,
                             ArtistService artistService, StatisticService statisticService) {
        this.userService = userService;
        this.playlistService = playlistService;
        this.songService = songService;
        this.streamingService = streamingService;
        this.artistService = artistService;
        this.statisticService = statisticService;
    }

    public User login(String login, String password) {
        return userService.login(login, password);
    }

    public boolean register(String login, String password, String ipAddress, String roleName) {
        return userService.register(login, password, ipAddress, roleName);
    }

    public Playlist createPlaylist(String name, String ownerId, Playlist.Status status) {
        return playlistService.createPlaylist(name, ownerId, status);
    }

    public void deletePlaylist(String playlistId) {
        playlistService.deletePlaylist(playlistId);
    }

    public void stopStreaming() {
        streamingService.stopStreaming();
    }

    public void playSong(String songId, String quality) {
        Song song = songService.findSongById(songId);
        if (song != null) {
            String songPath = song.getFilePath();
            streamingService.startStreaming(songPath, quality);
        } else {
            System.out.println("Song not found with id: " + songId);
        }
    }


    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    public void addSong(Song song) {
        songService.addSong(song);
    }

    public void removeSong(String songId) {
        songService.removeSong(songId);
    }
    public Artist addArtist(String name) {
        return artistService.addArtist(name);
    }

    public List<Statistic> getStatistics(String userId) {
        return statisticService.getUserStatistics(userId);
    }
}
