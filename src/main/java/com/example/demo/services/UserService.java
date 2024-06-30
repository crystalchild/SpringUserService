package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.saveUser(user);
    }

    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    public User getUser(UUID id) {
        return userRepository.getUser(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public String deleteUser(UUID id) {
        userRepository.deleteUser(id);
        return "User: " + id + " Deleted";
    }
}
