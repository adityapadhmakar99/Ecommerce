package com.ap.payment;

public class CardPayment implements PaymentStrategy {
    public boolean pay(double amount){
        System.out.println("   Paid amount" + amount + " using card");
        return true;
    }
}
