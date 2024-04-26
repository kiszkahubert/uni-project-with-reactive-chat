package com.kiszka.restaurantpage.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiszka.restaurantpage.web.entity.forms.FormData;
import com.kiszka.restaurantpage.web.entity.forms.OrderData;
import com.kiszka.restaurantpage.web.entity.orderinfo.OrderDetailsDto;
import com.kiszka.restaurantpage.web.entity.orderinfo.OrderService;
import com.kiszka.restaurantpage.web.services.EmailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

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
        return "pages/mainpage";
    }
    @GetMapping("/home/menu")
    public String getMenu(){
        return "pages/order";
    }
    @GetMapping("/home/basket")
    public String getBasket(){
        return "pages/basket";
    }
    @GetMapping("/home/contact")
    public String getContact(){
        return "pages/contactme";
    }
    @PostMapping("/api/endpoint")
    public ResponseEntity<String> receiveData(@Valid @RequestBody FormData data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString("Data has been received");
        emailService.sendEmail(data);
        return ResponseEntity.ok(jsonResponse);
    }
    @PostMapping("/api/orders")
    public ResponseEntity<String> receiveData(@Valid @RequestBody List<OrderData> orders) throws JsonProcessingException {
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
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString("Data has been received");
        return ResponseEntity.ok(jsonResponse);
    }
    @GetMapping("/")
    public String getProfile(){
        return "/pages/mainpage";
    }
    @GetMapping("/home/profile")
    public String getProfile(Model model){
        List<OrderDetailsDto> orders = orderService.getOrdersForCurrentUser();
        model.addAttribute("orders",orders);
        return "pages/profile";
    }
}
@ControllerAdvice
class GlobalExceptionHandler{
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFoundError(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/home");
        mav.setStatus(HttpStatus.MOVED_PERMANENTLY);
        return mav;
    }
}