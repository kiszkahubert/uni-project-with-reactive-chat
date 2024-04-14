package com.kiszka.restaurantpage.web.entity.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormData {
    @Pattern(regexp = "^[a-zA-Z]{3,20}", message="Podaj tylko litery")
    String name;
    @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9]+[.][a-zA-Z]{1,8}$", message = "Zły format")
    String email;
    @Pattern(regexp = "[0-9]{9}", message = "Podaj tylko 9 cyfr")
    String phoneNumber;
    @Pattern(regexp = "[a-zA-Z0-9 ]{1,30}$", message = "Podaj tylko do 30 znaków")
    String topic;
    @Pattern(regexp = "^.{1,200}$", message = "Tylko 200 znaków")
    @JsonProperty("message")
    String message;
}
