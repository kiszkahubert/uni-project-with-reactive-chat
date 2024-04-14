package com.kiszka.restaurantpage.webchat.chatuser;

import com.kiszka.restaurantpage.web.entity.validation.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUserService {
    private final ChatUserRepository repository;
    private final

    @Autowired
    public ChatUserService(ChatUserRepository repository) {
        this.repository = repository;
    }

    public void saveChatUser(ChatUser user){

        user.setStatus(Status.ONLINE);
        repository.save(user);
    }
    public void disconnect(ChatUser user){
        var storedUser = repository.findById(user.getEmail())
                .orElse(null);
        if(storedUser != null){
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }
    public List<ChatUser> findConnectedChatUsers(){
        return repository.findAllByStatus(Status.ONLINE);
    }
}
