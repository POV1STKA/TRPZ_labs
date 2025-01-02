package com.OnlineRadio.OnlineRadioStation.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "playlist_song")
@Getter
@Setter
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "songId")
    private Long songId;

    @Column(name = "playlistId")
    private Long playlistId;

    public PlaylistSong() {}

    public PlaylistSong(Long songId, Long playlistId) {
        this.songId = songId;
        this.playlistId = playlistId;
    }
}