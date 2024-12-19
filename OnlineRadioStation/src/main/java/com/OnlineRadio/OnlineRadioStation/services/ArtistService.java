package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.Artist;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class ArtistService {

    private final List<Artist> artists = new ArrayList<>();

    public Artist addArtist(String name) {
        Artist artist = new Artist(generateId(), name);
        artists.add(artist);
        return artist;
    }

    public Artist getArtistById(String artistId) {
        return artists.stream()
                .filter(artist -> artist.getId().equals(artistId))
                .findFirst()
                .orElse(null);
    }

    public List<Artist> getAllArtists() {
        return new ArrayList<>(artists);
    }

    public void removeArtist(String artistId) {
        artists.removeIf(artist -> artist.getId().equals(artistId));
    }

    private String generateId() {
        return java.util.UUID.randomUUID().toString();
    }
}
