package com.kiszka.restaurantpage.entity.database;

import com.kiszka.restaurantpage.entity.database.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo,Integer> {
    UserInfo findByEmail(String email);
}
