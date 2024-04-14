package com.kiszka.restaurantpage.webchat.chatuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatUserController {
    private final ChatUserService service;
}
