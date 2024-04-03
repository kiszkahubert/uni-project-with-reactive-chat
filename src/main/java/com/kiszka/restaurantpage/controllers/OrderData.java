package com.kiszka.restaurantpage.controllers;

import lombok.Data;

@Data
public class OrderData {
    private String item;
    private String sauce;
    private String meat;
    private int quantity;
    private int totalPrice;
}
