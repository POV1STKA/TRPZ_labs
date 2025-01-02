package com.OnlineRadio.OnlineRadioStation.models;

import com.mpatric.mp3agic.Mp3File;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "song")
@Getter
@Setter
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "filePath")
    private String filePath;

    public Song(String title, String filePath) {
        this.title = title;
        this.filePath = filePath;
        calculateDuration();
    }

    public Song() {}

    public long calculateDuration() {
        try {
            Mp3File mp3File = new Mp3File(filePath);
            if (mp3File.hasId3v1Tag() || mp3File.hasId3v2Tag()) {
                return mp3File.getLengthInSeconds();
            }
        } catch (Exception e) {
            System.err.println("Error calculating duration for file: " + filePath);
            e.printStackTrace();
        }
        return 0;
    }
}