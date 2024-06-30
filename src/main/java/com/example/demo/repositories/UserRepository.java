package com.example.demo.repositories;


import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final HashMap<UUID, User> map = new HashMap<>();

    public User saveUser(User user) {
        map.put(user.getId(), user);
        return user;
    }

    public User getUser(UUID id) {
        return map.get(id);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        if (map.isEmpty()) return users;
        for (Map.Entry<UUID, User> entry : map.entrySet()) {
            users.add(entry.getValue());
        }
        return users;
    }

    public User updateUser(User user) {
        map.put(user.getId(), user);
        return user;
    }

    public void deleteUser(UUID id) {
        map.remove(id);
    }
}
