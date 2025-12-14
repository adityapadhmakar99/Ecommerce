package com.ap;

import com.ap.entity.*;
import com.ap.repo.*;
import com.ap.service.*;

import java.util.List;

import static com.ap.constants.PaymentMode.CARD;

public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        SellerRepo sellerRepo = new SellerRepo();
        ProductRepo productRepo = new ProductRepo();
        InventoryRepo inventoryRepo = new InventoryRepo();
        CartRepo cartRepo = new CartRepo();
        OrderRepo orderRepo = new OrderRepo();
        UserRepo userRepo = new UserRepo();
        PaymentRepo paymentRepo = new PaymentRepo();

        // Initialize services
        SellerService sellerService = new SellerService(sellerRepo);
        ProductService productService = new ProductService(productRepo, sellerService);
        InventoryService inventoryService = new InventoryService(inventoryRepo);
        CartService cartService = new CartService(cartRepo);
        PaymentService paymentService = new PaymentService(paymentRepo);
        UserService userService = new UserService(userRepo, cartService);
        OrderService orderService = new OrderService(paymentService, inventoryService, cartService, sellerService, productRepo, orderRepo);

        System.out.println("=== E-Commerce System Demo ===");

        // 1. Seller Registration
        System.out.println("\n1. Registering a new seller...");
        Seller techStore = sellerService.createSeller("Tech Store", "tech@example.com");
        System.out.println("   Seller created: " + techStore.getName() + " (ID: " + techStore.getId() + ")");

        // 2. Add Products
        System.out.println("\n2. Adding products...");
        Product laptop = productService.createProduct("Laptop", 999.99, techStore.getId());
        Product phone = productService.createProduct("Smartphone", 699.99, techStore.getId());
        System.out.println("   Products added:");
        System.out.println("   - " + laptop.getName() + " ($" + laptop.getPrice() + ")");
        System.out.println("   - " + phone.getName() + " ($" + phone.getPrice() + ")");

        // 3. Add Inventory
        System.out.println("\n3. Adding inventory...");
        inventoryService.addStock(laptop.getId(), techStore.getId(), 10);
        inventoryService.addStock(phone.getId(), techStore.getId(), 20);
        System.out.println("   Inventory updated:");
        System.out.println("   - Laptops: " +
                inventoryService.getAvailableQuantity(laptop.getId(), techStore.getId()));
        System.out.println("   - Phones: " +
                inventoryService.getAvailableQuantity(phone.getId(), techStore.getId()));

        // 4. Shopping Cart
        System.out.println("\n4. Shopping cart operations...");
        User buyer = userService.createUser("John Doe"); // Assuming User has a simple constructor

        System.out.println("   Adding items to cart...");
        cartService.addToCart(buyer, laptop, 1);
        cartService.addToCart(buyer, phone, 2);

        // Get the cart and print items with quantities
        Cart cart = cartService.getCart(buyer.getId());
        System.out.println("   Items in cart (" + cart.getItems().size() + "):");
        cart.getItems().forEach(item ->
                System.out.println("   - " + item.getProduct().getName() + " x" + item.getQuantity() +
                        " ($" + (item.getProduct().getPrice() * item.getQuantity()) + ")")
        );

        // 5. Place Order
        System.out.println("\n5. Placing order...");
        try {
            Order order = orderService.placeOrder(buyer, CARD);
            System.out.println("   Order placed successfully! Order ID: " + order.getId());
            System.out.println("   Order : " + order);
            // 6. Check updated inventory
            System.out.println("\n6. Updated inventory after order:");
            System.out.println("   - Laptops: " +
                    inventoryService.getAvailableQuantity(laptop.getId(), techStore.getId()));
            System.out.println("   - Phones: " +
                    inventoryService.getAvailableQuantity(phone.getId(), techStore.getId()));

        } catch (Exception e) {
            System.err.println("   Error placing order: " + e.getMessage());
        }

        System.out.println("\n 7. User History for: " + buyer.getName() + " (ID: " + buyer.getId() + ")");
        
        // Display Orders
        System.out.println("\n  Orders:");
        System.out.println("  " + "-".repeat(80));
        List<Order> orders = orderService.getOrders(buyer.getId());
        if (orders == null || orders.isEmpty()) {
            System.out.println("  No orders found");
        } else {
            for (Order order : orders) {
                System.out.println("  Order ID: " + order.getId());
                System.out.println("  Status: " + order.getStatus());
                System.out.println("  Amount: $" + order.getAmount());
                System.out.println("  Items: " + (order.getItems() != null ? order.getItems().size() : 0));
                System.out.println("  " + "-".repeat(40));
            }
        }
        
        // Display Payments
        System.out.println("\n  Payment History:");
        System.out.println("  " + "-".repeat(80));
        List<Payment> payments = paymentService.getPayments(buyer.getId());
        if (payments == null || payments.isEmpty()) {
            System.out.println("  No payment history found");
        } else {
            for (Payment payment : payments) {
                System.out.println("  Payment ID: " + payment.getId());
                System.out.println("  Amount: $" + payment.getAmount());
                System.out.println("  Status: " + payment.getStatus());
                System.out.println("  " + "-".repeat(40));
            }
        }

        System.out.println("\n=== Demo Complete ===");
    }
}