package com.OnlineRadio.OnlineRadioStation.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Statistic {
    private String id;
    private String userId;
    private LocalDateTime visitTime;
    private int listeningDuration;
    private List<String> songIds;

    public Statistic(String id, String userId, LocalDateTime visitTime, int listeningDuration) {
        this.id = id;
        this.userId = userId;
        this.visitTime = visitTime;
        this.listeningDuration = listeningDuration;
        this.songIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

    public int getListeningDuration() {
        return listeningDuration;
    }

    public void setListeningDuration(int listeningDuration) {
        this.listeningDuration = listeningDuration;
    }

    public List<String> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<String> songIds) {
        this.songIds = songIds;
    }

    public void addSongId(String songId) {
        songIds.add(songId);
    }
}
