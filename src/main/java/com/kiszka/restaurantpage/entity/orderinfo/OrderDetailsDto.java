package com.kiszka.restaurantpage.entity.orderinfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class OrderDetailsDto {
    private String item;
    private int quantity;
    private int totalPrice;
    private Date orderDate;
    private Date deliveryDate;
    private String sauce;
    private String meat;
}
