package com.kiszka.restaurantpage.entity.orderinfo;

import com.kiszka.restaurantpage.entity.validation.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findByUser(UserInfo userInfo);
}
