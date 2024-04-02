package com.kiszka.restaurantpage.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo,Integer> {
    UserInfo findByEmail(String email);
}
