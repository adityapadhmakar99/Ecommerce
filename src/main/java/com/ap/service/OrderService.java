package com.ap.service;

import com.ap.constants.OrderStatus;
import com.ap.constants.PaymentMode;
import com.ap.constants.PaymentStatus;
import com.ap.entity.*;
import com.ap.repo.CartRepo;
import com.ap.repo.InventoryRepo;
import com.ap.repo.OrderRepo;
import com.ap.repo.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderService {
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final CartService cartService;
    private final SellerService sellerService;
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public OrderService(PaymentService paymentService, InventoryService inventoryService, CartService cartService, SellerService sellerService, ProductRepo productRepo, OrderRepo orderRepo) {
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
        this.cartService = cartService;
        this.sellerService = sellerService;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }


    public Order placeOrder(User user, PaymentMode paymentMode) {
        // 1. Get user's cart
        Cart cart = cartService.getCart(user.getId());
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // 2. Validate inventory and collect seller information
        for (Item item : cart.getItems()) {
            UUID productId = item.getProduct().getId();
            UUID sellerId = item.getProduct().getSellerId();

            // Check if product exists and is active
            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

            // Verify seller is active
            if (sellerService.getSeller(sellerId) == null) {
                throw new RuntimeException("Invalid seller for product: " + product.getName());
            }

            // Check stock availability
            int availableQty = inventoryService.getAvailableQuantity(productId, sellerId);
            if (availableQty < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName() +
                        ". Available: " + availableQty);
            }
        }

        // 3. Calculate total amount
        double amount = Math.round(cart.getItems().stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum());

        // 4. Process payment
        boolean paymentSuccess = paymentService.processPayment(user, amount, paymentMode);

        // 5. Create order
        Order order = new Order(
                UUID.randomUUID(),
                user.getId(),
                new ArrayList<>(cart.getItems()),
                paymentSuccess ? OrderStatus.PLACED : OrderStatus.PAYMENT_FAILED,
                amount
        );

        if (paymentSuccess) {
            try {
                // 6. Update inventory
                for (Item item : cart.getItems()) {
                    UUID productId = item.getProduct().getId();
                    UUID sellerId = item.getProduct().getSellerId();
                    inventoryService.removeStock(productId, sellerId, item.getQuantity());
                }

                // 7. Save the order
                orderRepo.save(order);
                orderRepo.save(user, order);

                // 8. Clear the cart
                cart.getItems().clear();
                cartService.saveCart(cart);

            } catch (Exception e) {
                // In case of any error, mark order as failed
                order.setStatus(OrderStatus.FAILED);
                orderRepo.save(order);
                throw new RuntimeException("Order processing failed: " + e.getMessage(), e);
            }
        } else {
            orderRepo.save(order);
        }

        return order;
    }

    public List<Order> getOrders(UUID id) {
        return orderRepo.getOrders(id);
    }
}
