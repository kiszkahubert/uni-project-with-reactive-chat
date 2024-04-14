package com.kiszka.restaurantpage.webchat.chatuser;

import com.kiszka.restaurantpage.webchat.chatuser.pojo.ChatUser;
import com.kiszka.restaurantpage.webchat.chatuser.pojo.ChatUserRepository;
import com.kiszka.restaurantpage.webchat.chatuser.pojo.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUserService {

    private final ChatUserRepository chatUserRepository;

    @Autowired
    public ChatUserService(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    public void saveChatUser(ChatUser chatUser){
        chatUser.setStatus(Status.ONLINE);
        chatUserRepository.save(chatUser);
    }
    public void disconnect(ChatUser chatUser){
        var connectedChatUser = chatUserRepository.findById(chatUser.getEmail())
                .orElse(null);
        if(connectedChatUser != null){
            connectedChatUser.setStatus(Status.OFFLINE);
            chatUserRepository.save(connectedChatUser);
        }

    }
    public List<ChatUser> findConnectedChatUsers(){
        return chatUserRepository.findAllByStatus(Status.ONLINE);
    }
}
