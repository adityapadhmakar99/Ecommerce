package com.ap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Cart {
    private UUID userId;
    private List<Item> items;

    public Cart(UUID userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
    }
}
