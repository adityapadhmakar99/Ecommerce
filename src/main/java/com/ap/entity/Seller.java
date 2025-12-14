package com.ap.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Seller {
    private UUID id;
    private String name;
    private String email;
    private String gstID;

    public Seller(String name, String email, String gstID) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.gstID = gstID;
    }
}
