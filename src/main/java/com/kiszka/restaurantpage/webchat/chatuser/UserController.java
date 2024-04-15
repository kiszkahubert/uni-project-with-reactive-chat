package com.kiszka.restaurantpage.webchat.chatuser;

import com.kiszka.restaurantpage.webchat.chatuser.pojo.ChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final ChatUserService chatUserService;

    @Autowired
    public UserController(ChatUserService chatUserService) {
        this.chatUserService = chatUserService;
    }

    @MessageMapping("/user.addUser")//after user is connected send message to queue
    @SendTo("/user/public") //new queue which you can pull data from, we need to subscribe to it from front so we get connected users
    public ChatUser addUser(@Payload ChatUser chatUser){
        chatUserService.saveChatUser(chatUser);
        return chatUser;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public ChatUser disconnect(@Payload ChatUser chatUser){
        chatUserService.disconnect(chatUser);
        return chatUser;
    }
    @GetMapping("/users")
    public ResponseEntity<List<ChatUser>> findConnectedUsers(){ //czyli to powinno byc po stronie admina zeby mogl widziec wszystkich
        return ResponseEntity.ok(chatUserService.findConnectedChatUsers());
    }
}
