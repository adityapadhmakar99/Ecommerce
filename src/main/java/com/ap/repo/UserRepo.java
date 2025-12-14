package com.ap.repo;

import com.ap.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserRepo {
    private final Map<UUID, User> users = new HashMap<>();

    public void save(User user){
        users.put(user.getId(), user);
    }

    public User getUser(UUID userId){
        return users.get(userId);
    }

    public List<User> getAllUsers() {
        return users.values().stream().toList();
    }
}
