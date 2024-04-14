package com.kiszka.restaurantpage.webchat.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ChatMessage {
    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId; //TODO TO DELETE
    private String content;
    private Date timestamp;
}
