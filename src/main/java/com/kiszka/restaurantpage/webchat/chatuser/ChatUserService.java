package com.kiszka.restaurantpage.webchat.chatuser;

import com.kiszka.restaurantpage.web.entity.validation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUserService {
    private final ChatUserRepository repository;
    private final UserServiceImpl userService;

    @Autowired
    public ChatUserService(ChatUserRepository repository, UserServiceImpl userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public void saveChatUser(ChatUser user){
        user.setEmail(userService.getCurrentUser().getEmail());
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
