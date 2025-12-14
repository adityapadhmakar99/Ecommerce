package com.ap.repo;

import com.ap.entity.Cart;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CartRepo {
    private final Map<UUID, Cart> carts = new HashMap<>();

    public Cart getCart(UUID userId){
        return carts.computeIfAbsent(userId, Cart::new);
    }

    public Cart saveCart(Cart cart){
        carts.put(cart.getUserId(), cart);
        return cart;
    }
}
