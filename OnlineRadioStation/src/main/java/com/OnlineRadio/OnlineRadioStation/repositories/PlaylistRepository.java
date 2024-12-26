package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlaylistRepository {
    private final Map<String, Playlist> playlists = new HashMap<>();

    public void insert(Playlist playlist) {
        playlists.put(playlist.getId(), playlist);
    }

    public Playlist findById(String playlistId) {
        return playlists.get(playlistId);
    }

    public List<Playlist> findAll() {
        return new ArrayList<>(playlists.values());
    }

    public void deleteById(String playlistId) {
        playlists.remove(playlistId);
    }

    public void update(Playlist playlist) {
        playlists.put(playlist.getId(), playlist);
    }
}
