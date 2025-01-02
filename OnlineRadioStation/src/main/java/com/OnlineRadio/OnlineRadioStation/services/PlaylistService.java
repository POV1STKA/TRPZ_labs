package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.models.PlaylistSong;
import com.OnlineRadio.OnlineRadioStation.repositories.PlaylistRepository;
import com.OnlineRadio.OnlineRadioStation.repositories.PlaylistSongRepository;
import com.OnlineRadio.OnlineRadioStation.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistSongRepository playlistSongRepository;

    @Autowired
    private SongRepository songRepository;

    public Playlist getPlaylistById(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found with id: " + id));
    }

    public List<Playlist> searchPlaylistsByName(String name) {
        return playlistRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public List<Song> getAllSongsInPlaylist(long playlistId) {
        List<PlaylistSong> playlistSongs = playlistSongRepository.findByPlaylistId(playlistId);
        List<Song> songs = new ArrayList<>();
        for (PlaylistSong playlistSong : playlistSongs) {
            songRepository.findById(playlistSong.getSongId()).ifPresent(songs::add);
        }
        return songs;
    }

    public Playlist addPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Playlist updatePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(long id) {
        playlistRepository.deleteById(id);
    }

    public void addSongToPlaylist(long playlistId, long songId) {
        PlaylistSong playlistSong = new PlaylistSong();
        playlistSong.setPlaylistId(playlistId);
        playlistSong.setSongId(songId);
        playlistSongRepository.save(playlistSong);
    }

    public void removeSongFromPlaylist(long playlistId, long songId) {
        playlistSongRepository.findByPlaylistId(playlistId).stream()
                .filter(ps -> ps.getSongId() == songId)
                .forEach(playlistSongRepository::delete);
    }
}
