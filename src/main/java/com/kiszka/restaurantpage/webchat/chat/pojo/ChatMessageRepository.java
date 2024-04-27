package com.kiszka.restaurantpage.webchat.chat.pojo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage,String> {
    List<ChatMessage> findByChatIdAndStatus(String chatId,String status);
    List<ChatMessage> findBySenderIdAndRecipientId(String senderId,String recipientId);
}
