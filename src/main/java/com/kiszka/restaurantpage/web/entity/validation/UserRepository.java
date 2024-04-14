package com.kiszka.restaurantpage.web.entity.validation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo,Integer> {
    UserInfo findByEmail(String email);
    UserInfo findByRole(String role);
}
