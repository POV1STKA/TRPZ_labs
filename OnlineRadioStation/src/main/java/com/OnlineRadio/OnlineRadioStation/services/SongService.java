package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.Artist;
import com.OnlineRadio.OnlineRadioStation.services.SongConverter;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.models.SongArtist;
import com.OnlineRadio.OnlineRadioStation.repositories.ArtistRepository;
import com.OnlineRadio.OnlineRadioStation.repositories.SongArtistRepository;
import com.OnlineRadio.OnlineRadioStation.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongConverter songConverter;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongArtistRepository songArtistRepository;

    public Song getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + id));
    }

    public List<Song> searchSongsByTitle(String title) {
        return songRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public List<Artist> getAllArtistsForSong(long songId) {
        List<SongArtist> songArtists = songArtistRepository.findBySongId(songId);
        List<Artist> artists = new ArrayList<>();
        for (SongArtist songArtist : songArtists) {
            artistRepository.findById(songArtist.getArtistId()).ifPresent(artists::add);
        }
        return artists;
    }
    public Song addSong(Song song) {
        if (song.getId() != null){
            song.setFilePath(songRepository.getById(song.getId()).getFilePath());
            return songRepository.save(song);
        }
        songConverter.convertToMultipleQualities(song);
        return songRepository.save(song);
    }

    public void deleteSong(long id) {
        songRepository.deleteById(id);
    }

    public void addArtistToSong(long songId, long artistId) {
        SongArtist songArtist = new SongArtist();
        songArtist.setSongId(songId);
        songArtist.setArtistId(artistId);
        songArtistRepository.save(songArtist);
    }

    public void removeArtistFromSong(long songId, long artistId) {
        songArtistRepository.findBySongId(songId).stream()
                .filter(sa -> sa.getArtistId() == artistId)
                .forEach(songArtistRepository::delete);
    }
}
