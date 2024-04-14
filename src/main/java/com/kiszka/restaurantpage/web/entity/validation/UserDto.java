package com.kiszka.restaurantpage.web.entity.validation;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {
    @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9]+[.][a-zA-Z]{1,8}$", message = "Email w formacie abcd@abcd.abcd")
    private String email;
    @Pattern(regexp = "^.{8,20}", message = "Hasło od 8 do 20 znaków")
    private String password;
    private String role;
}
