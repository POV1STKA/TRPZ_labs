package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final OnlineRadioFacade facade;

    @Autowired
    public SongController(OnlineRadioFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return facade.getAllSongs();
    }

    @PostMapping
    public void addSong(@RequestBody Song song) {
        facade.addSong(song);
    }

    @DeleteMapping("/{songId}")
    public void removeSong(@PathVariable String songId) {
        facade.removeSong(songId);
    }
}