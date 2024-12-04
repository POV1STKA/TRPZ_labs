package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import com.OnlineRadio.OnlineRadioStation.models.Song;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {
    private List<Playlist> playlists;
    private SongService songService;

    public PlaylistService(SongService songService) {
        this.playlists = new ArrayList<>();
        this.songService = songService;
    }

    public void createPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public void addSongToPlaylist(String playlistId, String songId) {
        Playlist playlist = findPlaylistById(playlistId);
        if (playlist != null) {
            playlist.getSongIds().add(songId);
        }
    }

    private Playlist findPlaylistById(String playlistId) {
        return playlists.stream()
                .filter(p -> p.getId().equals(playlistId))
                .findFirst()
                .orElse(null);
    }

    public List<Song> getSongsFromPlaylist(String playlistId) {
        Playlist playlist = findPlaylistById(playlistId);
        if (playlist == null) return new ArrayList<>();

        List<Song> result = new ArrayList<>();
        for (String songId : playlist.getSongIds()) {
            result.addAll(songService.getSongs().stream()
                    .filter(song -> song.getId().equals(songId))
                    .toList());
        }
        return result;
    }
}
