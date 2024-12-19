package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.streaming.*;
import com.OnlineRadio.OnlineRadioStation.factories.AudioStreamFactory;
import org.springframework.stereotype.Service;
@Service
public class StreamingService {
    private AudioStream audioStream;

    public void startStreaming(String songPath, String quality) {
        if (audioStream != null) {
            System.out.println("A stream is already running.");
            return;
        }

        audioStream = AudioStreamFactory.createStream(quality);

        audioStream.startStream(songPath);
        System.out.println("Streaming started with " + audioStream.getStreamQuality());
    }

        public void stopStreaming() {
        if (audioStream == null) {
            System.out.println("No stream is currently running.");
            return;
        }

        audioStream.stopStream();
        System.out.println("Streaming stopped.");
        audioStream = null;
    }
}
