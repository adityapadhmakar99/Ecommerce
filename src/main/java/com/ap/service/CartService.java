package com.ap.service;

import com.ap.entity.Cart;
import com.ap.entity.Item;
import com.ap.entity.Product;
import com.ap.entity.User;
import com.ap.repo.CartRepo;

import java.awt.*;
import java.util.UUID;

public class CartService {
    private final CartRepo cartRepo;

    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public void createCart(User user){
        Cart cart = new Cart(user.getId());
        cartRepo.saveCart(cart);
    }

    public void addToCart(User user, Product product, int qty){
        Cart cart = cartRepo.getCart(user.getId());
        cart.getItems().add(new Item(product, qty));
    }

    public Cart getCart(UUID userId) {
        return cartRepo.getCart(userId);
    }

    public void saveCart(Cart cart) {
        cartRepo.saveCart(cart);
    }
}
