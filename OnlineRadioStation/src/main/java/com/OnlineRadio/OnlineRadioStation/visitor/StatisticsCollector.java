package com.OnlineRadio.OnlineRadioStation.visitor;

import com.OnlineRadio.OnlineRadioStation.models.User;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.models.Playlist;

public class StatisticsCollector implements StatisticsVisitor {

    private int totalSongs = 0;
    private int totalPlaylists = 0;
    private int totalArtists = 0;

    @Override
    public void visitUser(User user) {
        System.out.println("Processing user: " + user.getLogin());
        totalPlaylists += user.getPlaylistIds().size();
    }

    @Override
    public void visitSong(Song song) {
        System.out.println("Processing song: " + song.getTitle());
        totalArtists += song.getArtistIds().size();
        totalSongs++;
    }

    @Override
    public void visitPlaylist(Playlist playlist) {
        System.out.println("Processing playlist: " + playlist.getName());
    }

    public int getTotalSongs() {
        return totalSongs;
    }

    public int getTotalPlaylists() {
        return totalPlaylists;
    }

    public int getTotalArtists() {
        return totalArtists;
    }
}
