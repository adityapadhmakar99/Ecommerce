package com.ap.service;

import com.ap.entity.User;
import com.ap.repo.UserRepo;

import java.util.List;
import java.util.UUID;

public class UserService {
    private final UserRepo userRepo;
    private final CartService cartService;

    public UserService (UserRepo userRepo, CartService cartService){
        this.userRepo = userRepo;
        this.cartService = cartService;
    }

    public User createUser(String name){
        User user = new User(name);
        userRepo.save(user);
        cartService.createCart(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    public User getUserById(UUID userId) {
        return userRepo.getUser(userId);
    }
}
