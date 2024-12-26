package com.OnlineRadio.OnlineRadioStation.models;

import java.util.ArrayList;
import java.util.List;
import com.OnlineRadio.OnlineRadioStation.visitor.StatisticsVisitor;
import com.OnlineRadio.OnlineRadioStation.visitor.Visitable;

public class Playlist implements Visitable{
    public enum Status { PUBLIC, PRIVATE, CLOSED }

    private String id;
    private String name;
    private List<String> songIds;
    private String ownerId;
    private Status status;

    public Playlist(String id, String name, String ownerId, Status status) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.status = status;
        this.songIds = new ArrayList<>();
    }

    @Override
    public void accept(StatisticsVisitor visitor) {
        visitor.visitPlaylist(this);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<String> songIds) {
        this.songIds = songIds;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addSongId(String songId) {
        songIds.add(songId);
    }

    public void removeSongId(String songId) {
        songIds.remove(songId);
    }

}