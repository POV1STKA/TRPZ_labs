package com.OnlineRadio.OnlineRadioStation.models;

import java.util.List;
import java.util.ArrayList;

public class Song {
    private String id;
    private String title;
    private List<String> artistIds;

    public Song(String id, String title) {
        this.id = id;
        this.title = title;
        this.artistIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getArtistIds() {
        return artistIds;
    }

    public void setArtistIds(List<String> artistIds) {
        this.artistIds = artistIds;
    }

    public void addArtistId(String artistId) {
        artistIds.add(artistId);
    }
}
