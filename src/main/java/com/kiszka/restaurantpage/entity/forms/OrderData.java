package com.kiszka.restaurantpage.entity.forms;

import jakarta.validation.constraints.Max;
import lombok.Data;

@Data
public class OrderData {
    private String item;
    private String sauce;
    private String meat;
    @Max(value = 10,message = "Ilość nie może przekraczać 10")
    private int quantity;
    private int totalPrice;
}
