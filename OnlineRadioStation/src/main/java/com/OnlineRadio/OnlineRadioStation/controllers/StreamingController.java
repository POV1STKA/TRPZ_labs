package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/streaming")
public class StreamingController {

    @Autowired
    private OnlineRadioFacade onlineRadioFacade;

    @PostMapping("/play/{id}")
    public String playSong(@PathVariable("id") String songId, @RequestParam("quality") String quality) {
        try {
            onlineRadioFacade.playSong(songId, quality);
            return "Streaming started with " + quality + " quality.";
        } catch (Exception e) {
            return "Error starting stream: " + e.getMessage();
        }
    }

    @PostMapping("/stop")
    public String stopStreaming() {
        try {
            onlineRadioFacade.stopStreaming();
            return "Streaming stopped.";
        } catch (Exception e) {
            return "Error stopping stream: " + e.getMessage();
        }
    }
}
