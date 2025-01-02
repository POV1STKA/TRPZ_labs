package com.OnlineRadio.OnlineRadioStation.factories;

import com.OnlineRadio.OnlineRadioStation.adapters.*;
import com.OnlineRadio.OnlineRadioStation.models.Song;

public abstract class QualityAdapterCreator {
    public abstract QualityAdapter createAdapter();

    public Song convertSong(Song song) {
        QualityAdapter adapter = createAdapter();
        return adapter.convert(song);
    }
}
