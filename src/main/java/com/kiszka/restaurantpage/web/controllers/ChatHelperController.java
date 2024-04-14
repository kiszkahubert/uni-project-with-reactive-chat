package com.kiszka.restaurantpage.web.controllers;

import com.kiszka.restaurantpage.web.entity.validation.UserInfo;
import com.kiszka.restaurantpage.web.entity.validation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatHelperController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/admin")
    public UserInfo getAdminUser() {
        return userService.getAdmin();
    }
}
