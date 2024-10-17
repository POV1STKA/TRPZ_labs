package com.OnlineRadio.OnlineRadioStation.repositories;
import com.OnlineRadio.OnlineRadioStation.models.Playlist;

import java.util.HashMap;
import java.util.Map;

public class PlaylistRepository {
    private Map<String, Playlist> playlists = new HashMap<>();

    public void addPlaylist(Playlist playlist) {
        playlists.put(playlist.getId(), playlist);
    }

    public Playlist findPlaylistById(String id) {
        return playlists.get(id);
    }
}

