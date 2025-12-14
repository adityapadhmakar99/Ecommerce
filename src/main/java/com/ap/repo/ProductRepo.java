// src/main/java/com/ap/repo/ProductRepo.java
package com.ap.repo;

import com.ap.entity.Product;

import java.util.*;

public class ProductRepo {
    private final Map<UUID, Product> products = new HashMap<>();
    private final Map<UUID, List<Product>> productsBySeller = new HashMap<>();

    public Product save(Product product) {
        products.put(product.getId(), product);
        
        // Maintain seller-product relationship
        productsBySeller.computeIfAbsent(product.getSellerId(), k -> new ArrayList<>())
                      .add(product);
                      
        return product;
    }

    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public List<Product> findBySeller(UUID sellerId) {
        return new ArrayList<>(productsBySeller.getOrDefault(sellerId, Collections.emptyList()));
    }

    public boolean existsById(UUID id) {
        return products.containsKey(id);
    }

    public void deleteById(UUID id) {
        Product product = products.remove(id);
        if (product != null) {
            // Remove from seller's product list
            productsBySeller.computeIfPresent(product.getSellerId(), 
                (k, v) -> {
                    v.removeIf(p -> p.getId().equals(id));
                    return v.isEmpty() ? null : v;
                });
        }
    }
}