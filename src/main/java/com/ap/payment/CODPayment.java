package com.ap.payment;

public class CODPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("   Paid amount: " + amount + " using COD");
        return true;
    }
}
