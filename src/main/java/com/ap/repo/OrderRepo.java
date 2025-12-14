package com.ap.repo;

import com.ap.entity.Order;
import com.ap.entity.User;

import java.util.*;

public class OrderRepo {
    private final Map<UUID, Order> orders = new HashMap<>();
    private final Map<UUID, List<Order>> userOrders = new HashMap<>();

    public void save(Order order){
        orders.put(order.getId(), order);
    }

    public void save(User user, Order order) {
        userOrders.computeIfAbsent(user.getId(), k -> new ArrayList<>()).add(order);
    }

    public List<Order> getOrders(UUID userId) {
        return userOrders.get(userId);
    }
}
