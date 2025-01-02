package com.OnlineRadio.OnlineRadioStation.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "song_artist")
@Getter
@Setter
public class SongArtist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "songId")
    private Long songId;

    @Column(name = "artistId")
    private Long artistId;

    public SongArtist() {}

    public SongArtist(Long songId, Long artistId) {
        this.songId = songId;
        this.artistId = artistId;
    }
}