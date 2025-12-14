package com.ap.repo;

import com.ap.entity.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InventoryRepo {
    private final Map<String, Inventory> inventories = new HashMap<>();

    public void save(Inventory inventory){
        inventories.put(inventory.getSellerId()+":"+inventory.getProductId(), inventory);
    }

    public Inventory getInventory(UUID sellerId, UUID productId){
        return inventories.get(sellerId+":"+productId);
    }

    public Optional<Inventory> findByProductAndSeller(UUID productId, UUID sellerId) {
        return Optional.ofNullable(inventories.get(sellerId + ":" + productId));
    }
}
