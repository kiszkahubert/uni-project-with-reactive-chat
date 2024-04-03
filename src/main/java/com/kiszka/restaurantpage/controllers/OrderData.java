package com.kiszka.restaurantpage.controllers;

import lombok.Data;

@Data
public class OrderData {
    private String item;
    private String sos;
    private String mieso;
    private int quantity;
    private int totalPrice;
}
