package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import com.OnlineRadio.OnlineRadioStation.models.User;
import com.OnlineRadio.OnlineRadioStation.models.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final OnlineRadioFacade facade;

    @Autowired
    public UserController(OnlineRadioFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/login")
    public User login(@RequestParam String login, @RequestParam String password) {
        return facade.login(login, password);
    }

    @PostMapping("/register")
    public boolean register(@RequestParam String login, @RequestParam String password,
                            @RequestParam String ipAddress, @RequestParam String roleName) {
        return facade.register(login, password, ipAddress, roleName);
    }

    @GetMapping("/{userId}/statistics")
    public List<Statistic> getStatistics(@PathVariable String userId) {
        return facade.getStatistics(userId);
    }
}

