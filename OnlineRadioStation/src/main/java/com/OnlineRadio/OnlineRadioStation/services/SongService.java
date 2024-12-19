package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.repositories.SongRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void addSong(Song song) {
        songRepository.addSong(song);
    }

    public void removeSong(String songId) {
        songRepository.removeSong(songId);
    }

    public Song findSongById(String songId) {
        return songRepository.findSongById(songId);
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(songRepository.getAllSongs());
    }
}
