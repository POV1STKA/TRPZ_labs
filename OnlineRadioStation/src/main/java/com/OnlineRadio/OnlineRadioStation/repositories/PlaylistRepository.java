package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PlaylistRepository {
    private final List<Playlist> playlists = new ArrayList<>();

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public void removePlaylist(String playlistId) {
        playlists.removeIf(playlist -> playlist.getId().equals(playlistId));
    }

    public Playlist findPlaylistById(String playlistId) {
        return playlists.stream().filter(playlist -> playlist.getId().equals(playlistId)).findFirst().orElse(null);
    }

    public List<Playlist> getAllPlaylists() {
        return new ArrayList<>(playlists);
    }
}
