package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.Artist;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
@Repository
public class ArtistRepository {
    private Map<String, Artist> artists = new HashMap<>();

    public void addArtist(Artist artist) {
        artists.put(artist.getId(), artist);
    }

    public Artist findArtistById(String id) {
        return artists.get(id);
    }

    public Artist findArtistByName(String name) {
        return artists.values().stream()
                .filter(artist -> artist.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}

