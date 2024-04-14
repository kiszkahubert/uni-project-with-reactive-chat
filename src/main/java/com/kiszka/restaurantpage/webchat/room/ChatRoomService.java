package com.kiszka.restaurantpage.webchat.room;

import com.kiszka.restaurantpage.web.entity.validation.UserServiceImpl;
import com.kiszka.restaurantpage.webchat.room.pojo.ChatRoom;
import com.kiszka.restaurantpage.webchat.room.pojo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserServiceImpl userService;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository, UserServiceImpl userService) {
        this.chatRoomRepository = chatRoomRepository;
        this.userService = userService;
    }

    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(ChatRoom::getChatId)
                .or(()->{
                    if (createNewRoomIfNotExists){
                        var chatId = createChat();
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createChat() {
        var senderId = userService.getCurrentUser().getEmail();
        var recipientId = userService.getAdmin().getEmail();
        var chatId = String.format("%s_%s",senderId,recipientId);
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();
        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return chatId;
    }
}
