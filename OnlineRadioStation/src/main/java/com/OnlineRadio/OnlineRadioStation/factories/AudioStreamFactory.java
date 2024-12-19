package com.OnlineRadio.OnlineRadioStation.factories;

import com.OnlineRadio.OnlineRadioStation.models.streaming.*;

public class AudioStreamFactory {

    public static AudioStream createStream(String quality) {
        switch (quality.toLowerCase()) {
            case "low":
                return new LowQualityStream();
            case "medium":
                return new MediumQualityStream();
            case "high":
                return new HighQualityStream();
            default:
                throw new IllegalArgumentException("Unknown stream quality: " + quality);
        }
    }
}
