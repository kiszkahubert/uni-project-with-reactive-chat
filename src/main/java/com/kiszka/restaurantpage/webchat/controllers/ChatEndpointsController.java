package com.kiszka.restaurantpage.webchat.controllers;

import com.kiszka.restaurantpage.web.entity.validation.UserInfo;
import com.kiszka.restaurantpage.web.entity.validation.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ChatEndpointsController {
    private final UserService userService;

    @Autowired
    public ChatEndpointsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/help/chat")
    public String getChatPage(){
        UserInfo userInfo = userService.getCurrentUser();
        String role = userInfo.getRole();
        if(role.equals("ADMIN")){
            return "chat/adminPage";
        }
        return "chat/userPage";
    }
}
