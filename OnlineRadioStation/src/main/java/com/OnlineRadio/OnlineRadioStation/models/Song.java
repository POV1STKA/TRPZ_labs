package com.OnlineRadio.OnlineRadioStation.models;

import java.util.List;
import java.util.ArrayList;

public class Song {
    private String id;
    private String title;
    private List<String> artistIds;
    private String filePath;

    public Song(String id, String title, String filePath) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.artistIds = new ArrayList<>();
    }

    public Song(String id, String title, String filePath, List<String> artistIds) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.artistIds = artistIds;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
