package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.streaming.AudioStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamingServiceTest {

    @Test
    void testStartLowQualityStreaming() {
        StreamingService streamingService = new StreamingService();
        streamingService.startStreaming("low");

        AudioStream stream = streamingService.getCurrentStream();
        assertNotNull(stream);
        assertEquals(64, stream.getBitrate());
        assertEquals("Low Quality (64 kbps)", stream.getStreamQuality());
    }

    @Test
    void testStartMediumQualityStreaming() {
        StreamingService streamingService = new StreamingService();
        streamingService.startStreaming("medium");

        AudioStream stream = streamingService.getCurrentStream();
        assertNotNull(stream);
        assertEquals(128, stream.getBitrate());
        assertEquals("Medium Quality (128 kbps)", stream.getStreamQuality());
    }

    @Test
    void testStartHighQualityStreaming() {
        StreamingService streamingService = new StreamingService();
        streamingService.startStreaming("high");

        AudioStream stream = streamingService.getCurrentStream();
        assertNotNull(stream);
        assertEquals(256, stream.getBitrate());
        assertEquals("High Quality (256 kbps)", stream.getStreamQuality());
    }
}
