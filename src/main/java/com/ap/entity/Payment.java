package com.ap.entity;

import com.ap.constants.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Payment {
    private UUID id;
    private double amount;
    private PaymentStatus status;
}
