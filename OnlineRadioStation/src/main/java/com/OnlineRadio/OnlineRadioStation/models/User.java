package com.OnlineRadio.OnlineRadioStation.models;

import java.util.List;
import java.util.ArrayList;

public class User {
    private String id;
    private String login;
    private String password;
    private String ipAddress;
    private Role role;
    private List<String> statisticIds;
    private List<String> playlistIds;

    public User(String id, String login, String password, String ipAddress, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.ipAddress = ipAddress;
        this.role = role;
        this.statisticIds = new ArrayList<>();
        this.playlistIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<String> getStatisticIds() {
        return statisticIds;
    }

    public void setStatisticIds(List<String> statisticIds) {
        this.statisticIds = statisticIds;
    }

    public List<String> getPlaylistIds() {
        return playlistIds;
    }

    public void setPlaylistIds(List<String> playlistIds) {
        this.playlistIds = playlistIds;
    }

    public void addPlaylistId(String playlistId) {
        playlistIds.add(playlistId);
    }
}