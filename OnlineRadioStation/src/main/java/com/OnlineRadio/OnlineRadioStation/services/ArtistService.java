package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.Artist;
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
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongArtistRepository songArtistRepository;

    @Autowired
    private SongRepository songRepository;

    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + id));
    }

    public List<Artist> searchArtistsByName(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public List<Song> getAllSongsByArtist(long artistId) {
        List<SongArtist> songArtists = songArtistRepository.findByArtistId(artistId);
        List<Song> songs = new ArrayList<>();
        for (SongArtist songArtist : songArtists) {
            songRepository.findById(songArtist.getSongId()).ifPresent(songs::add);
        }
        return songs;
    }

    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist updateArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public void deleteArtist(long id) {
        artistRepository.deleteById(id);
    }

    public void addSongToArtist(long artistId, long songId) {
        SongArtist songArtist = new SongArtist();
        songArtist.setArtistId(artistId);
        songArtist.setSongId(songId);
        songArtistRepository.save(songArtist);
    }

    public void removeSongFromArtist(long artistId, long songId) {
        songArtistRepository.findByArtistId(artistId).stream()
                .filter(sa -> sa.getSongId() == songId)
                .forEach(songArtistRepository::delete);
    }
}
