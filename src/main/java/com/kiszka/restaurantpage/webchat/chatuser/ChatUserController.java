package com.kiszka.restaurantpage.webchat.chatuser;

import lombok.RequiredArgsConstructor;
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
public class ChatUserController {
    private final ChatUserService service;
    @MessageMapping("/user.addUser")
    @SendTo("/user/{userId}/public")
    public ChatUser addUser(@Payload ChatUser user, @DestinationVariable("userId")String userId){
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
