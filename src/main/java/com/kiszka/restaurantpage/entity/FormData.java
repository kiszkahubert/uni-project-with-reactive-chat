package com.kiszka.restaurantpage.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormData {
    String name;
    String email;
    String phoneNumber;
    String topic;
    @JsonProperty("message")
    String message;
}
