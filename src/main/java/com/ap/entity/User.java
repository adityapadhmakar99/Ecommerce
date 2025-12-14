package com.ap.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String name;

    public User(String name) {
        id = UUID.randomUUID();
        this.name = name;
    }
}

