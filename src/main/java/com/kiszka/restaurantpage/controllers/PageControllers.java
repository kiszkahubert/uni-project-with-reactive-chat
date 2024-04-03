package com.kiszka.restaurantpage.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiszka.restaurantpage.entity.orderinfo.OrderDetailsDto;
import com.kiszka.restaurantpage.entity.orderinfo.OrderService;
import com.kiszka.restaurantpage.services.EmailService;
import jakarta.persistence.criteria.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class PageControllers {
    private final EmailService emailService;
    private final OrderService orderService;
    @Autowired
    public PageControllers(EmailService emailService, OrderService orderService){
        this.emailService = emailService;
        this.orderService = orderService;
    }
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
        emailService.sendEmail(data);
        return ResponseEntity.ok(jsonResponse);
    }
    @PostMapping("/api/orders")
    public ResponseEntity<String> receiveData(@RequestBody List<OrderData> orders) throws JsonProcessingException {
        for (var obj : orders){
            orderService.saveOrder(new OrderDetailsDto(
                    obj.getItem(),
                    obj.getQuantity(),
                    obj.getTotalPrice(),
                    new Date(),
                    null,
                    obj.getSauce(),
                    obj.getMeat()
            ));
            log.info(obj.toString());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString("Data has been received");
        return ResponseEntity.ok(jsonResponse);
    }
}
