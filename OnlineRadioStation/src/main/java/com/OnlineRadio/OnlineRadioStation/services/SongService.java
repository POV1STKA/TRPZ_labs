package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.adapters.FullSongToSongAdapter;
import com.OnlineRadio.OnlineRadioStation.models.FullSong;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.iterators.SongIterator;
import com.OnlineRadio.OnlineRadioStation.iterators.RandomSongIterator;

import java.util.ArrayList;
import java.util.List;

public class SongService {
    private List<Song> songs;

    public SongService() {
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void addSong(FullSong fullSong) {
        FullSongToSongAdapter adapter = new FullSongToSongAdapter(fullSong);
        songs.add(adapter.adapt());
    }

    public List<Song> getSongs() {
        return songs;
    }

    public SongIterator getSongIterator() {
        return new SongIterator(songs);
    }

    public RandomSongIterator getRandomSongIterator() {
        return new RandomSongIterator(songs);
    }
}
