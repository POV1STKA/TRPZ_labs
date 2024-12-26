package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.Song;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SongRepository {
    private final Map<String, Song> songs = new HashMap<>();

    public void insert(Song song) {
        songs.put(song.getId(), song);
    }

    public Song findById(String songId) {
        return songs.get(songId);
    }

    public List<Song> findAll() {
        return new ArrayList<>(songs.values());
    }

    public void deleteById(String songId) {
        songs.remove(songId);
    }

    public void update(Song song) {
        songs.put(song.getId(), song);
    }
}
