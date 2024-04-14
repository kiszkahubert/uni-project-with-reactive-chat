package com.kiszka.restaurantpage.webchat.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ChatRoom {
    @Id
    private String id;
    private String chatId;
    private String senderId;
}