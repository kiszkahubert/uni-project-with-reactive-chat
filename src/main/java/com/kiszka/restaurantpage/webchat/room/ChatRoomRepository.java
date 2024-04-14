package com.kiszka.restaurantpage.webchat.room;

import java.util.Optional;

public interface ChatRoomRepository {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
