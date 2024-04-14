package com.kiszka.restaurantpage.webchat.chat;

import com.kiszka.restaurantpage.webchat.room.ChatRoomService;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertFalseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {
    private final ChatMessageService messageService;
    private final ChatRoomService chatRoomService;
    private final AssertFalseValidator assertFalseValidator;

    @Autowired
    public ChatMessageService(ChatMessageService messageService, ChatRoomService chatRoomService, AssertFalseValidator assertFalseValidator) {
        this.messageService = messageService;
        this.chatRoomService = chatRoomService;
        this.assertFalseValidator = assertFalseValidator;
    }
    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService.
                getChatRoomId(
                        chatMessage.getSenderId(),
                        chatMessage.getRecipientId(),
                        true)
                .orElseThrow();
        chatMessage.setChatId(chatId);
        messageService.save(chatMessage);
        return chatMessage;
    }
    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId,recipientId,false);
        return chatId.map(messageService::findByChatId).orElse(new ArrayList<>());
    }
}
