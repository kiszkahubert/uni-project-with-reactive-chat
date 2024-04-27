package com.kiszka.restaurantpage.webchat.room;

import com.kiszka.restaurantpage.web.entity.validation.UserService;
import com.kiszka.restaurantpage.webchat.room.pojo.ChatRoom;
import com.kiszka.restaurantpage.webchat.room.pojo.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(ChatRoom::getChatId)
                .or(()->{
                    if (createNewRoomIfNotExists){
                        String chatId = createChat(senderId,recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    public void deleteChat(String senderId, String recipientId){
        chatRoomRepository.deleteBySenderIdAndRecipientId(senderId,recipientId);
        chatRoomRepository.deleteByRecipientIdAndSenderId(recipientId,senderId);
    }

    private String createChat(String senderId, String recipientId) {
        if(senderId != null && recipientId != null){
            var chatId = String.format("%s_%s",senderId,recipientId);
            ChatRoom senderRecipient = ChatRoom
                    .builder()
                    .chatId(chatId)
                    .senderId(senderId)
                    .recipientId(recipientId)
                    .build();

            ChatRoom recipientSender = ChatRoom
                    .builder()
                    .chatId(chatId)
                    .senderId(recipientId)
                    .recipientId(senderId)
                    .build();

            chatRoomRepository.save(senderRecipient);
            chatRoomRepository.save(recipientSender);
            return chatId;
        }
        return null;
    }
}
