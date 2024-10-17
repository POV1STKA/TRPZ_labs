package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.Artist;
import java.util.HashMap;
import java.util.Map;

public class ArtistRepository {
    private Map<String, Artist> artists = new HashMap<>();

    public void addArtist(Artist artist) {
        artists.put(artist.getId(), artist);
    }

    public Artist findArtistById(String id) {
        return artists.get(id);
    }
}

