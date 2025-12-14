// src/main/java/com/ap/service/SellerService.java
package com.ap.service;

import com.ap.entity.Seller;
import com.ap.repo.SellerRepo;
import java.util.List;
import java.util.UUID;

public class SellerService {
    private final SellerRepo sellerRepo;

    public SellerService(SellerRepo sellerRepo) {
        this.sellerRepo = sellerRepo;
    }

    public Seller createSeller(String name, String email) {
        Seller seller = new Seller(name, email, "GST" + UUID.randomUUID());
        sellerRepo.save(seller);
        return seller;
    }

    public Seller getSeller(UUID sellerId) {
        return sellerRepo.getById(sellerId);
    }

    public List<Seller> getAllSellers() {
        return sellerRepo.getAll();
    }
}