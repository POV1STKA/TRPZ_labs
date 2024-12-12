package com.OnlineRadio.OnlineRadioStation.models.streaming;

public abstract class AudioStream {
    protected int bitrate;

    public AudioStream(int bitrate) {
        this.bitrate = bitrate;
    }

    public abstract String getStreamQuality();

    public int getBitrate() {
        return bitrate;
    }

    public void startStream() {
        System.out.println("Streaming started at " + bitrate + " kbps.");
    }
}
