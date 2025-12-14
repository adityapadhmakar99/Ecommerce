package com.ap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class Inventory {
    private UUID sellerId;
    private UUID productId;
    private int quantity;

    public Inventory(UUID sellerId, UUID productId, int quantity) {
        this.sellerId = sellerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public void addStock(int qty){
        this.quantity += qty;
    }

    public boolean removeStock(int qty){
        if(this.quantity >= qty){
            this.quantity -= qty;
            return true;
        }
        return false;
    }
}
