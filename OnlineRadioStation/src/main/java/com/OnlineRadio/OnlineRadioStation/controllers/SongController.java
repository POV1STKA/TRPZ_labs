package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.services.SongService;

import java.util.List;

public class SongController {
    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    public void addSong(Song song) {
        songService.addSong(song);
    }

    public List<Song> getAllSongs() {
        return songService.getSongs();
    }

    public void playSongsInOrder() {
        System.out.println("Playing songs in order:");
        var iterator = songService.getSongIterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            System.out.println("Playing: " + song.getTitle());
        }
    }

    public void playSongsInRandomOrder() {
        System.out.println("Playing songs in random order:");
        var iterator = songService.getRandomSongIterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            System.out.println("Playing: " + song.getTitle());
        }
    }
}
