package com.kiszka.restaurantpage.entity.orderinfo;

import lombok.Data;

@Data
public class OrderData {
    private String item;
    private String sos;
    private String mieso;
    private int quantity;
    private int totalPrice;
}
