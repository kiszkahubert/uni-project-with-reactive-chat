package com.kiszka.restaurantpage.webchat.room.pojo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom,String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
    void deleteBySenderIdAndRecipientId(String senderId, String recipientId);
    void deleteByRecipientIdAndSenderId(String recipientId, String senderId);
}
