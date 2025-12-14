// src/main/java/com/ap/repo/SellerRepo.java
package com.ap.repo;

import com.ap.entity.Seller;
import java.util.*;

public class SellerRepo {
    private final Map<UUID, Seller> sellers = new HashMap<>();

    public void save(Seller seller) {
        sellers.put(seller.getId(), seller);
    }

    public Seller getById(UUID id) {
        return sellers.get(id);
    }

    public List<Seller> getAll() {
        return new ArrayList<>(sellers.values());
    }
}