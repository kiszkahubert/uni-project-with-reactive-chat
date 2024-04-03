package com.kiszka.restaurantpage.entity.validation;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    UserInfo findUserByEmail(String email);
    UserInfo getCurrentUser();
    List<UserDto> findAllUsers();
}
