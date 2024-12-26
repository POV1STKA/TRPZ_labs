package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void addSong(Song song) {
        songRepository.insert(song);
    }

    public void removeSong(String songId) {
        songRepository.deleteById(songId);
    }

    public Song findSongById(String songId) {
        return songRepository.findById(songId);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}
