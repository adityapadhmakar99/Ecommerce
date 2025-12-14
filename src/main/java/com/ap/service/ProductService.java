// src/main/java/com/ap/service/ProductService.java
package com.ap.service;

import com.ap.entity.Product;
import com.ap.repo.ProductRepo;
import java.util.List;
import java.util.UUID;

public class ProductService {
    private final ProductRepo productRepo;
    private final SellerService sellerService;

    public ProductService(ProductRepo productRepo, SellerService sellerService) {
        this.productRepo = productRepo;
        this.sellerService = sellerService;
    }

    public Product createProduct(String name, double price, UUID sellerId) {
        // Verify seller exists
        if (sellerService.getSeller(sellerId) == null) {
            throw new RuntimeException("Seller not found with id: " + sellerId);
        }
        
        Product product = new Product(name, price, sellerId);
        return productRepo.save(product);
    }

    public List<Product> getProductsBySeller(UUID sellerId) {
        return productRepo.findBySeller(sellerId);
    }
}