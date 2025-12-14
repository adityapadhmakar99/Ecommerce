package com.ap.payment;

import com.ap.constants.PaymentMode;

import static com.ap.constants.PaymentMode.CARD;
import static com.ap.constants.PaymentMode.COD;

public class PaymentFactory {
    public static PaymentStrategy get (PaymentMode mode){
        if(CARD.equals(mode))
            return new CODPayment();
        if(COD.equals(mode))
            return new CardPayment();
        throw new IllegalArgumentException("Invalid mode");
    }
}
