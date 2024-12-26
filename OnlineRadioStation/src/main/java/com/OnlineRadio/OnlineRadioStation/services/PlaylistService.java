package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import com.OnlineRadio.OnlineRadioStation.repositories.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist createPlaylist(String name, String ownerId, Playlist.Status status) {
        Playlist playlist = new Playlist(generateId(), name, ownerId, status);
        playlistRepository.insert(playlist);
        return playlist;
    }

    public Playlist getPlaylistById(String playlistId) {
        return playlistRepository.findById(playlistId);
    }

    public void deletePlaylist(String playlistId) {
        playlistRepository.deleteById(playlistId);
    }

    public void updatePlaylist(Playlist playlist) {
        playlistRepository.update(playlist);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    private String generateId() {
        return java.util.UUID.randomUUID().toString();
    }
}
