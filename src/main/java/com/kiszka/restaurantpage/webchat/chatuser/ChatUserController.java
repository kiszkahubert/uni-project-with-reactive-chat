package com.kiszka.restaurantpage.webchat.chatuser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatUserController {

    private final ChatUserService service;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public ChatUser addUser(@Payload ChatUser user){
        service.saveChatUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public ChatUser disconnect(@Payload ChatUser user){
        service.disconnect(user);
        return user;
    }
    @GetMapping("/users")
    public ResponseEntity<List<ChatUser>> findConnectedUsers(){
        return ResponseEntity.ok(service.findConnectedChatUsers());
    }
}
