package com.kiszka.restaurantpage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormData {
    String name;
    String email;
    String phoneNumber;
    String topic;
    String message;
}
