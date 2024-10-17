package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.Song;
import java.util.HashMap;
import java.util.Map;

public class SongRepository {
    private Map<String, Song> songs = new HashMap<>();

    public void addSong(Song song) {
        songs.put(song.getId(), song);
    }

    public Song findSongById(String id) {
        return songs.get(id);
    }
}

