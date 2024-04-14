package com.kiszka.restaurantpage.web.entity.orderinfo;

import com.kiszka.restaurantpage.web.entity.validation.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findByUser(UserInfo userInfo);
}
