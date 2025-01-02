package com.OnlineRadio.OnlineRadioStation.controllers;

import com.OnlineRadio.OnlineRadioStation.facade.OnlineRadioFacade;
import com.OnlineRadio.OnlineRadioStation.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    private final OnlineRadioFacade facade;
    @Autowired
    public UserController(OnlineRadioFacade facade) {
        this.facade = facade;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return facade.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> searchUsersByLogin(@RequestParam String login) {
        return facade.searchUsersByLogin(login);
    }

    @GetMapping("/users/all")
    public List<User> getAllUsers() {
        return facade.getAllUsers();
    }

    @PostMapping("/users")
    public User addOrUpdateUser(@RequestBody User user) {
        return facade.addOrUpdateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        facade.deleteUser(id);
    }

    @GetMapping("/history")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> getUserHistory() {
        Map<String, Integer> userHistory = facade.getUserCountHistory();
        return ResponseEntity.ok(userHistory);
    }
}
