package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.streaming.AudioStream;
import com.OnlineRadio.OnlineRadioStation.factories.AudioStreamFactory;
import org.springframework.stereotype.Service;

@Service
public class StreamingService {

    private AudioStream currentStream;

    public void startStreaming(String quality) {
        currentStream = AudioStreamFactory.createStream(quality);
        System.out.println("Starting " + currentStream.getStreamQuality() + " stream...");
        currentStream.startStream();
    }

    public AudioStream getCurrentStream() {
        return currentStream;
    }
}
