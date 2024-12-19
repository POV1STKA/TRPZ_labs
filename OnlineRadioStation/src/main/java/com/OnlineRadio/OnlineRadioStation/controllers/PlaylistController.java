package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final OnlineRadioFacade facade;

    @Autowired
    public PlaylistController(OnlineRadioFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public Playlist createPlaylist(@RequestParam String name, @RequestParam String ownerId,
                                   @RequestParam Playlist.Status status) {
        return facade.createPlaylist(name, ownerId, status);
    }

    @DeleteMapping("/{playlistId}")
    public void deletePlaylist(@PathVariable String playlistId) {
        facade.deletePlaylist(playlistId);
    }

    @PostMapping("/{playlistId}/songs")
    public void addSongToPlaylist(@PathVariable String playlistId, @RequestParam String songId) {
        facade.addSongToPlaylist(playlistId, songId);
    }

    @DeleteMapping("/{playlistId}/songs")
    public void removeSongFromPlaylist(@PathVariable String playlistId, @RequestParam String songId) {
        facade.removeSongFromPlaylist(playlistId, songId);
    }

    @GetMapping("/{playlistId}/songs")
    public List<Song> getSongsFromPlaylist(@PathVariable String playlistId) {
        return facade.getSongsFromPlaylist(playlistId);
    }
}