package com.kiszka.restaurantpage.webchat.chatuser;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatUserRepository extends MongoRepository<ChatUser,String> {
    List<ChatUser> findAllByStatus(Status status);
}
