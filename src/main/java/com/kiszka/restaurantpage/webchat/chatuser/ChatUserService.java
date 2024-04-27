package com.kiszka.restaurantpage.webchat.chatuser;

import com.kiszka.restaurantpage.web.entity.validation.UserService;
import com.kiszka.restaurantpage.webchat.chat.pojo.ChatMessage;
import com.kiszka.restaurantpage.webchat.chat.pojo.ChatMessageRepository;
import com.kiszka.restaurantpage.webchat.chatuser.pojo.ChatUser;
import com.kiszka.restaurantpage.webchat.chatuser.pojo.ChatUserRepository;
import com.kiszka.restaurantpage.webchat.chatuser.pojo.Status;
import com.kiszka.restaurantpage.webchat.room.ChatRoomService;
import com.kiszka.restaurantpage.webchat.room.pojo.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChatUserService {

    private final ChatUserRepository chatUserRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatUserService(ChatUserRepository chatUserRepository,
                           ChatMessageRepository chatMessageRepository,
                           UserService userService,
                           ChatRoomService chatRoomService
                           ) {
        this.chatUserRepository = chatUserRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.userService = userService;
        this.chatRoomService = chatRoomService;
    }

    public void saveChatUser(ChatUser chatUser){
        chatUser.setStatus(Status.ONLINE);
        chatUserRepository.save(chatUser);
    }
    public void disconnect(ChatUser chatUser){
        log.info("{}",chatUser.getEmail());
        var connectedChatUser = chatUserRepository.findById(chatUser.getEmail()).orElse(null);
        if(connectedChatUser != null){
            List<ChatMessage> userMessages = chatMessageRepository.findBySenderIdAndRecipientId(connectedChatUser.getEmail(), userService.getAdmin().getEmail());
            List<ChatMessage> adminMessages = chatMessageRepository.findBySenderIdAndRecipientId(userService.getAdmin().getEmail(),connectedChatUser.getEmail());
            for (var msg : userMessages){
                msg.setStatus("DOWN");
            }
            for (var msg : adminMessages){
                msg.setStatus("DOWN");
            }
            chatMessageRepository.saveAll(userMessages);
            chatMessageRepository.saveAll(adminMessages);
        }
    }
    public List<ChatUser> findConnectedChatUsers(){
        return chatUserRepository.findAllByStatus(Status.ONLINE);
    }
}
