package com.kiszka.restaurantpage.entity.orderinfo;

import lombok.Data;

import java.util.Date;
@Data
public class OrderDetailsDto {
    private String item;
    private int quantity;
    private Date orderDate;
    private Date deliveryDate;
    private String sauce;
    private String meat;
}
