package com.OnlineRadio.OnlineRadioStation.visitor;

import com.OnlineRadio.OnlineRadioStation.models.Playlist;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import com.OnlineRadio.OnlineRadioStation.models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitorPatternTest {

    @Test
    void testStatisticsCollector() {
        User user = new User("1", "User 1", "password", "192.168.0.1");
        user.addPlaylistId("101");
        user.addPlaylistId("102");

        Song song1 = new Song("201", "Song A", "/music/songA.mp3");
        song1.addArtistId("301");
        song1.addArtistId("302");

        Song song2 = new Song("202", "Song B", "/music/songB.mp3");
        song2.addArtistId("303");

        Playlist playlist = new Playlist("101", "Chill Vibes", user.getId(), Playlist.Status.PUBLIC);
        playlist.addSongId(song1.getId());
        playlist.addSongId(song2.getId());

        List<Object> items = List.of(user, song1, song2, playlist);

        StatisticsCollector collector = new StatisticsCollector();

        items.forEach(item -> {
            if (item instanceof User) {
                ((User) item).accept(collector);
            } else if (item instanceof Song) {
                ((Song) item).accept(collector);
            } else if (item instanceof Playlist) {
                ((Playlist) item).accept(collector);
            }
        });

        assertEquals(2, collector.getTotalSongs(), "Total songs should be 2");
        assertEquals(2, collector.getTotalPlaylists(), "Total playlists should be 2");
        assertEquals(3, collector.getTotalArtists(), "Total artists should be 3");
    }
}
