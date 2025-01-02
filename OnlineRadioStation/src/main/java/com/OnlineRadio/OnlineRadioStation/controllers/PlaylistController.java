package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/playlists")
public class PlaylistController {

    private final OnlineRadioFacade facade;

    @Autowired
    public PlaylistController(OnlineRadioFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/all")
    public List<Playlist> getAllPlaylists() {
        return facade.getAllPlaylists();
    }

    @GetMapping
    public List<Playlist> searchPlaylistsByTitle(@RequestParam(required = false) String title) {
        return (title != null && !title.isEmpty())
                ? facade.searchPlaylistsByName(title)
                : facade.getAllPlaylists();
    }

    @GetMapping("/{id}")
    public Playlist getPlaylistById(@PathVariable Long id) {
        return facade.getPlaylistById(id);
    }

    @PostMapping
    public Playlist addOrUpdatePlaylist(@RequestBody Playlist playlist) {
        return facade.addOrUpdatePlaylist(playlist);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable Long id) {
        facade.deletePlaylist(id);
    }

    @GetMapping("/{playlistId}/songs")
    public List<Song> getAllSongsForPlaylist(@PathVariable Long playlistId) {
        return facade.getAllSongsInPlaylist(playlistId);
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public void addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        facade.addSongToPlaylist(playlistId, songId);
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    public void removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        facade.removeSongFromPlaylist(playlistId, songId);
    }
}
