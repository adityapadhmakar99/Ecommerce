package com.ap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private double price;
    private UUID sellerId;

    public Product(String name, double price, UUID sellerId) {
        this.name = name;
        this.price = price;
        this.sellerId = sellerId;
        id = UUID.randomUUID();
    }
}
