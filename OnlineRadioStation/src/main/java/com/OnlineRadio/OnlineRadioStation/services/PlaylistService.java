package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class PlaylistService {

    private final List<Playlist> playlists = new ArrayList<>();

    public Playlist createPlaylist(String name, String ownerId, Playlist.Status status) {
        Playlist playlist = new Playlist(generateId(), name, ownerId, status);
        playlists.add(playlist);
        return playlist;
    }

    public Playlist getPlaylistById(String playlistId) {
        return playlists.stream()
                .filter(playlist -> playlist.getId().equals(playlistId))
                .findFirst()
                .orElse(null);
    }

    public void deletePlaylist(String playlistId) {
        playlists.removeIf(playlist -> playlist.getId().equals(playlistId));
    }

    public void addSongToPlaylist(String playlistId, String songId) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            playlist.addSongId(songId);
        } else {
            throw new IllegalArgumentException("Playlist not found!");
        }
    }

    public void removeSongFromPlaylist(String playlistId, String songId) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            playlist.removeSongId(songId);
        } else {
            throw new IllegalArgumentException("Playlist not found!");
        }
    }

    public List<String> getSongIdsInPlaylist(String playlistId) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist not found!");
        }
        return playlist.getSongIds();
    }

    private String generateId() {
        return java.util.UUID.randomUUID().toString();
    }
}
