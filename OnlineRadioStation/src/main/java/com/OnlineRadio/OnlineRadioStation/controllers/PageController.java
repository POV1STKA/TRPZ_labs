package com.OnlineRadio.OnlineRadioStation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/admin/userPanel")
    public String usersPage() {
        return "userPanel";
    }

    @GetMapping("/admin/userStats")
    public String usersStatsPage() {
        return "userStats";
    }

    @GetMapping("/admin/songPanel")
    public String songsPage() {
        return "songPanel";
    }

    @GetMapping("/admin/artistPanel")
    public String artistsPage() {
        return "artistPanel";
    }

    @GetMapping("/admin/playlistPanel")
    public String playlistsPage() {
        return "playlistPanel";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
