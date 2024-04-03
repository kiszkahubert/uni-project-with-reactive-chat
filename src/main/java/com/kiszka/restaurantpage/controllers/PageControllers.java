package com.kiszka.restaurantpage.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiszka.restaurantpage.entity.FormData;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class PageControllers {
    @GetMapping("/home")
    public String mainPage(){
        return "/pages/mainpage";
    }
    @GetMapping("/home/menu")
    public String getMenu(){
        return "/pages/order";
    }
    @GetMapping("/home/basket")
    public String getBasket(){
        return "/pages/basket";
    }
    @GetMapping("/home/contact")
    public String getContact(){
        return "/pages/contactme";
    }
    @PostMapping("/api/endpoint")
    public ResponseEntity<String> receiveData(@RequestBody FormData data) throws JsonProcessingException {
        log.info(data.getName()+" "+data.getEmail()+" "+data.getPhoneNumber()+" "+data.getTopic()+" "+data.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString("Data has been received");
        return ResponseEntity.ok(jsonResponse);
    }
}
