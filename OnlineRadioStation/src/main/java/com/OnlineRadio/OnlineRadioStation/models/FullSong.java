package com.OnlineRadio.OnlineRadioStation.models;

import java.util.List;

public class FullSong {
    private String id;
    private String title;
    private List<Artist> artists;

    public FullSong(String id, String title, List<Artist> artists) {
        this.id = id;
        this.title = title;
        this.artists = artists;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Artist> getArtists() {
        return artists;
    }
}
