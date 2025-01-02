package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.factories.*;
import com.OnlineRadio.OnlineRadioStation.models.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongConverter {
    private static final QualityAdapterCreator[] CREATORS = {
            new Quality64Creator(),
            new Quality92Creator(),
            new Quality128Creator(),
            new Quality196Creator(),
            new Quality224Creator()
    };

    public List<Song> convertToMultipleQualities(Song song) {
        List<Song> convertedSongs = new ArrayList<>();
        for (QualityAdapterCreator creator : CREATORS) {
            convertedSongs.add(creator.convertSong(song));
        }
        return convertedSongs;
    }
}
