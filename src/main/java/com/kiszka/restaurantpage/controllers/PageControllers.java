package com.kiszka.restaurantpage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageControllers {
    @GetMapping("/home")
    public String mainPage(){
        return "/pages/mainpage";
    }
    @GetMapping("/home/menu")
    public String getMenu(){
        return "/pages/order";
    }
}
