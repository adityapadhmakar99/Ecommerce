package com.ap.service;

import com.ap.constants.PaymentMode;
import com.ap.constants.PaymentStatus;
import com.ap.entity.Payment;
import com.ap.entity.User;
import com.ap.payment.PaymentFactory;
import com.ap.payment.PaymentStrategy;
import com.ap.repo.PaymentRepo;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PaymentService {
    private final PaymentRepo paymentRepo;

    public PaymentService(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    public boolean processPayment(User user, double amount, PaymentMode mode){
        PaymentStrategy paymentStrategy = PaymentFactory.get(mode);
        boolean paid = paymentStrategy.pay(amount);
        if(paid){
            Payment payment = new Payment(UUID.randomUUID(), amount, PaymentStatus.SUCCESS);
            paymentRepo.save(user, payment);
            return true;
        }
        return false;
    }

    public List<Payment> getPayments(UUID buyerId) {
        List<Payment> payments = paymentRepo.getPayments(buyerId);
        return payments != null ? payments : Collections.emptyList();
    }
}
