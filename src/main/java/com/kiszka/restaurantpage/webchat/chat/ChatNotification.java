package com.kiszka.restaurantpage.webchat.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ChatNotification {
    private String id;
    private String senderId;
    private static String recipientId = "admin";
    private String content;
}
