package com.ap.entity;

import com.ap.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@ToString
public class Order {
    private UUID id;
    private UUID userId;
    private List<Item> items;
    private OrderStatus status;
    private double amount;
}
