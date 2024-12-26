package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public void insert(User user) {
        users.put(user.getId(), user);
    }

    public User findById(String userId) {
        return users.get(userId);
    }

    public User findByLogin(String login) {
        return users.values().stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login))
                .findFirst()
                .orElse(null);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public void deleteById(String userId) {
        users.remove(userId);
    }

    public void update(User user) {
        users.put(user.getId(), user);
    }
}
