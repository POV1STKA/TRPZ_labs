package com.OnlineRadio.OnlineRadioStation.iterators;

import com.OnlineRadio.OnlineRadioStation.models.Song;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomSongIterator implements Iterator<Song> {
    private List<Song> shuffledSongs;
    private int position;

    public RandomSongIterator(List<Song> songs) {
        this.shuffledSongs = new ArrayList<>(songs);
        Collections.shuffle(this.shuffledSongs);
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < shuffledSongs.size();
    }

    @Override
    public Song next() {
        return hasNext() ? shuffledSongs.get(position++) : null;
    }
}
