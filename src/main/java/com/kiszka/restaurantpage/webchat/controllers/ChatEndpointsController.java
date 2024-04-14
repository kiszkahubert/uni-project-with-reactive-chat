package com.kiszka.restaurantpage.webchat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatEndpointsController {
    @GetMapping("/chat")
    public String getAdmingPage(){
        return "chat/adminPage";
    }
}
