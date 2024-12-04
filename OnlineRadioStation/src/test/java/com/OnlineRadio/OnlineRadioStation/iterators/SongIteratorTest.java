package com.OnlineRadio.OnlineRadioStation.iterators;

import com.OnlineRadio.OnlineRadioStation.models.Song;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SongIteratorTest {

    @Test
    void testSequentialIterator() {
        Song song1 = new Song("1", "Song 1");
        Song song2 = new Song("2", "Song 2");
        List<Song> songs = List.of(song1, song2);

        SongIterator iterator = new SongIterator(songs);

        assertTrue(iterator.hasNext());
        assertEquals(song1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(song2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testRandomIterator() {
        Song song1 = new Song("1", "Song 1");
        Song song2 = new Song("2", "Song 2");
        List<Song> songs = List.of(song1, song2);

        RandomSongIterator iterator = new RandomSongIterator(songs);

        assertTrue(iterator.hasNext());
        Song firstSong = iterator.next();
        assertTrue(iterator.hasNext());
        Song secondSong = iterator.next();
        assertFalse(iterator.hasNext());

        assertTrue(songs.contains(firstSong));
        assertTrue(songs.contains(secondSong));
        assertNotEquals(firstSong, secondSong);
    }
}
