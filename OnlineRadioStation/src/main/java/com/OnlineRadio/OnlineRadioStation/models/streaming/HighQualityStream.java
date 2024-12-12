package com.OnlineRadio.OnlineRadioStation.models.streaming;

public class HighQualityStream extends AudioStream {
    public HighQualityStream() {
        super(256);
    }

    @Override
    public String getStreamQuality() {
        return "High Quality (256 kbps)";
    }
}
