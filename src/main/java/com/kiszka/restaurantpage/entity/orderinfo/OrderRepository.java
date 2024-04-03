package com.kiszka.restaurantpage.entity.orderinfo;

import com.kiszka.restaurantpage.entity.validation.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
    OrderDetails findByUser(UserInfo userInfo);
}
