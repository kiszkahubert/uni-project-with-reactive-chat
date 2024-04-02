package com.kiszka.restaurantpage.entity.database;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String role;
}
