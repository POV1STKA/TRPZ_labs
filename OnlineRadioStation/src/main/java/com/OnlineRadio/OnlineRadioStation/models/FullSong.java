package com.OnlineRadio.OnlineRadioStation.models;

import java.util.List;

public class FullSong {
    private String id;
    private String title;
    private List<Artist> artists;
    private String filePath;

    public FullSong(String id, String title, String filePath, List<Artist> artists) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}