package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.models.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/songs")
public class SongController {

    private final OnlineRadioFacade facade;

    @Autowired
    public SongController(OnlineRadioFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/all")
    public List<Song> getAllSongs() {
        return facade.getAllSongs();
    }

    @GetMapping
    public List<Song> searchSongsByTitle(@RequestParam(required = false) String title) {
        return (title != null && !title.isEmpty())
                ? facade.searchSongsByTitle(title)
                : facade.getAllSongs();
    }

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable Long id) {
        return facade.getSongById(id);
    }

    @PostMapping
    public Song addOrUpdateSong(@RequestBody Song song) {
        return facade.addOrUpdateSong(song);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id) {
        facade.deleteSong(id);
    }

    @GetMapping("/{songId}/artists")
    public List<Artist> getAllArtistsForSong(@PathVariable Long songId) {
        return facade.getAllArtistsForSong(songId);
    }

    @PostMapping("/{songId}/artists/{artistId}")
    public void addArtistToSong(@PathVariable Long songId, @PathVariable Long artistId) {
        facade.addArtistToSong(songId, artistId);
    }

    @DeleteMapping("/{songId}/artists/{artistId}")
    public void removeArtistFromSong(@PathVariable Long songId, @PathVariable Long artistId) {
        facade.removeArtistFromSong(songId, artistId);
    }
}
