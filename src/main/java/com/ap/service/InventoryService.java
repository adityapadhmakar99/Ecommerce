package com.ap.service;

import com.ap.entity.Inventory;
import com.ap.repo.InventoryRepo;
import java.util.UUID;

public class InventoryService {
    private final InventoryRepo inventoryRepo;

    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    public void addStock(UUID productId, UUID sellerId, int quantity) {
        Inventory inventory = new Inventory(sellerId, productId, quantity);
        inventoryRepo.save(inventory);
    }

    public boolean removeStock(UUID productId, UUID sellerId, int quantity) {
        return inventoryRepo.findByProductAndSeller(productId, sellerId)
                .map(inventory -> {
                    if (inventory.getQuantity() >= quantity) {
                        inventory.removeStock(quantity);
                        inventoryRepo.save(inventory);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }

    public int getAvailableQuantity(UUID productId, UUID sellerId) {
        return inventoryRepo.findByProductAndSeller(productId, sellerId)
                .map(Inventory::getQuantity)
                .orElse(0);
    }
}