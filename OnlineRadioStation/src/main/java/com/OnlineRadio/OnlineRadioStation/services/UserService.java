package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.User;
import com.OnlineRadio.OnlineRadioStation.models.Role;
import com.OnlineRadio.OnlineRadioStation.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(String login, String password, String ipAddress, String roleName) {
        if (userRepository.findByLogin(login) != null) {
            return false;
        }
        Role role = new Role(roleName);
        User newUser = new User(String.valueOf(System.currentTimeMillis()), login, password, ipAddress, role);
        userRepository.insert(newUser);
        return true;
    }

    public User login(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User findUserById(String userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
