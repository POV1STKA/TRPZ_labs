package com.OnlineRadio.OnlineRadioStation.adapters;

import com.OnlineRadio.OnlineRadioStation.models.Artist;
import com.OnlineRadio.OnlineRadioStation.models.FullSong;
import com.OnlineRadio.OnlineRadioStation.models.Song;

import java.util.List;
import java.util.stream.Collectors;

public class FullSongToSongAdapter implements SongAdapter {
    private FullSong fullSong;

    public FullSongToSongAdapter(FullSong fullSong) {
        this.fullSong = fullSong;
    }

    @Override
    public Song adapt() {
        List<String> artistIds = fullSong.getArtists()
                .stream()
                .map(Artist::getId)
                .collect(Collectors.toList());

        return new Song(fullSong.getId(), fullSong.getTitle(), artistIds);
    }
}
