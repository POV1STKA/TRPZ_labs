package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import com.OnlineRadio.OnlineRadioStation.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class OnlineRadioController {
    private final Set<String> activeUsers = new HashSet<>();

    private final OnlineRadioFacade facade;

    @Autowired
    public OnlineRadioController(OnlineRadioFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/admin/queue")
    @ResponseBody
    public Queue<Song> getQueue() {
        return facade.getQueue();
    }

    @PostMapping("/admin/next")
    @ResponseBody
    public void playNextSong() {
        facade.playNextSong();
    }

    @PostMapping("/admin/clear")
    @ResponseBody
    public void clearQueue() {
        facade.clearQueue();
    }

    @PostMapping("/admin/add/{songId}")
    public ResponseEntity<String> addToQueue(@PathVariable Long songId) {
        try {
            facade.addToQueue(songId);
            return ResponseEntity.ok("Song added to queue.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/admin/addPlaylist/{playlistId}")
    public ResponseEntity<String> addPlaylistToQueue(@PathVariable Long playlistId,
                                                     @RequestParam(defaultValue = "false") boolean shuffle) {
        try {
            facade.addPlaylistToQueue(playlistId, shuffle);
            return ResponseEntity.ok("Playlist added to queue.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/sync")
    public Map<String, Object> syncWithRadio() {
        return facade.syncWithRadio();
    }

    @GetMapping("/stream")
    public ResponseEntity<StreamingResponseBody> streamSong(@RequestParam int bitrate) throws IOException {
        return facade.steamSong(bitrate);
    }

    @GetMapping("/history")
    @ResponseBody
    public ResponseEntity<Map<String, Song>> getSongHistory() {
        Map<String, Song> songHistory =  facade.getFormattedSongHistory();
        return ResponseEntity.ok(songHistory);
    }
}
