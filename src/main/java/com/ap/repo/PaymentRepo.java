package com.ap.repo;

import com.ap.entity.Payment;
import com.ap.entity.User;

import java.util.*;

public class PaymentRepo {
    private final Map<UUID, List<Payment>> paymentStore = new HashMap<>();

    public Payment save(User user, Payment payment) {
        // Get the current list of payments for the user, or create a new one if it doesn't exist
        List<Payment> userPayments = paymentStore.computeIfAbsent(user.getId(), k -> new ArrayList<>());
        // Add the new payment to the list
        userPayments.add(payment);
        return payment;
    }


    public List<Payment> getPayments(UUID buyerId) {
        return paymentStore.get(buyerId);
    }
}
