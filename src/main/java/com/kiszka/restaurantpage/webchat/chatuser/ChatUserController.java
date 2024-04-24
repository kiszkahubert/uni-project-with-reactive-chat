package com.kiszka.restaurantpage.webchat.chatuser;

import com.kiszka.restaurantpage.webchat.chatuser.pojo.ChatUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class ChatUserController {
    private final ChatUserService chatUserService;

    @Autowired
    public ChatUserController(ChatUserService chatUserService) {
        this.chatUserService = chatUserService;
    }

    @MessageMapping("/user.addUser")//after user is connected send message to queue
    @SendTo("/user/public") //new queue which you can pull data from, we need to subscribe to it from front so we get connected users
    public ChatUser addUser(@Payload ChatUser chatUser){
        log.info("{}:{}",chatUser.getEmail(),chatUser.getStatus());
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
