package com.OnlineRadio.OnlineRadioStation.models.streaming;

public class LowQualityStream extends AudioStream {
    public LowQualityStream() {
        super(64);
    }

    @Override
    public String getStreamQuality() {
        return "Low Quality (64 kbps)";
    }
}
