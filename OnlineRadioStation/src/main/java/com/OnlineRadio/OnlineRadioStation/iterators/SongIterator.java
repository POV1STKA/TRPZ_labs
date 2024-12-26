package com.OnlineRadio.OnlineRadioStation.iterators;

import com.OnlineRadio.OnlineRadioStation.models.Song;

import java.util.List;

public class SongIterator implements Iterator<Song> {
    private List<Song> songs;
    private int position;

    public SongIterator(List<Song> songs) {
        this.songs = songs;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public Song next() {
        return hasNext() ? songs.get(position++) : null;
    }
}
