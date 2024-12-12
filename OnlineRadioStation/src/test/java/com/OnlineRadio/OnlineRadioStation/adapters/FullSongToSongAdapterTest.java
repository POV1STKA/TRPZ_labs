package com.OnlineRadio.OnlineRadioStation.adapters;

import com.OnlineRadio.OnlineRadioStation.models.Artist;
import com.OnlineRadio.OnlineRadioStation.models.FullSong;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullSongToSongAdapterTest {
    @Test

    void testFullSongToSongAdapter() {
        Artist artist1 = new Artist("1", "Artist 1");
        Artist artist2 = new Artist("2", "Artist 2");
        FullSong fullSong = new FullSong("123", "Test Song", List.of(artist1, artist2));

        FullSongToSongAdapter adapter = new FullSongToSongAdapter(fullSong);
        Song adaptedSong = adapter.adapt();

        System.out.println("Adapted Song ID: " + adaptedSong.getId());
        System.out.println("Adapted Song Title: " + adaptedSong.getTitle());
        System.out.println("Adapted Song Artist IDs: " + adaptedSong.getArtistIds());

        assertEquals("123", adaptedSong.getId());
        assertEquals("Test Song", adaptedSong.getTitle());
        assertEquals(List.of("1", "2"), adaptedSong.getArtistIds());
    }

}
