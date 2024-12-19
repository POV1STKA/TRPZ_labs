package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.Song;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SongRepository {
    private final List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(String songId) {
        songs.removeIf(song -> song.getId().equals(songId));
    }

    public Song findSongById(String songId) {
        return songs.stream().filter(song -> song.getId().equals(songId)).findFirst().orElse(null);
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }
}
