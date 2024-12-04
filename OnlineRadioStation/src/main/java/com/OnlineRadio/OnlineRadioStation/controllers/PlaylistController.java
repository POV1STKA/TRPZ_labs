package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.services.PlaylistService;

import java.util.List;

public class PlaylistController {
    private PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    public void createPlaylist(Playlist playlist) {
        playlistService.createPlaylist(playlist);
    }

    public void addSongToPlaylist(String playlistId, String songId) {
        playlistService.addSongToPlaylist(playlistId, songId);
    }

    public List<Song> getSongsFromPlaylist(String playlistId) {
        return playlistService.getSongsFromPlaylist(playlistId);
    }
}
