package com.kiszka.restaurantpage.entity.database;

import com.kiszka.restaurantpage.entity.database.UserDto;
import com.kiszka.restaurantpage.entity.database.UserInfo;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    UserInfo findUserByEmail(String email);
    List<UserDto> findAllUsers();
}
