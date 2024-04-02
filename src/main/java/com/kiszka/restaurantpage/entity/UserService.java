package com.kiszka.restaurantpage.entity;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    UserInfo findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
