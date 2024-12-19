package com.OnlineRadio.OnlineRadioStation.models.streaming;

import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class AudioStream {
    protected int bitrate;
    protected AdvancedPlayer player;

    public AudioStream(int bitrate) {
        this.bitrate = bitrate;
    }

    public abstract String getStreamQuality();

    public int getBitrate() {
        return bitrate;
    }


    public void startStream(String filePath) {
        System.out.println("Playing" + filePath);
    }

    public void stopStream() {
        if (player != null) {
            player.close();
            System.out.println("Streaming stopped.");
        }
    }
}
