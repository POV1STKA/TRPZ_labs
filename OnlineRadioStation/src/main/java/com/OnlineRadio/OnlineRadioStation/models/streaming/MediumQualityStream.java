package com.OnlineRadio.OnlineRadioStation.models.streaming;

public class MediumQualityStream extends AudioStream {
    public MediumQualityStream() {
        super(128); // 128 kbps
    }

    @Override
    public String getStreamQuality() {
        return "Medium Quality (128 kbps)";
    }
}
