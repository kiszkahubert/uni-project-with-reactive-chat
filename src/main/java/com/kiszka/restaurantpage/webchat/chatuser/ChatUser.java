package com.kiszka.restaurantpage.webchat.chatuser;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ChatUser {
    @Id
    private String email;
    private Status status;
}

