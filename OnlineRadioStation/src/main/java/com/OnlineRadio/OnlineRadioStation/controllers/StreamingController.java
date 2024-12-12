package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.services.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/streaming")
public class StreamingController {

    @Autowired
    private StreamingService streamingService;

    @PostMapping("/start")
    public String startStreaming(@RequestParam String quality) {
        streamingService.startStreaming(quality);
        return "Streaming started with " + quality + " quality.";
    }

    @GetMapping("/current")
    public String getCurrentStream() {
        return "Current stream: " + streamingService.getCurrentStream().getStreamQuality();
    }
}
