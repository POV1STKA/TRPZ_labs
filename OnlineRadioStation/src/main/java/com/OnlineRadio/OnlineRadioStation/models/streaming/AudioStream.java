package com.OnlineRadio.OnlineRadioStation.models.streaming;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class AudioStream {
    protected int bitrate;

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
    }
}
