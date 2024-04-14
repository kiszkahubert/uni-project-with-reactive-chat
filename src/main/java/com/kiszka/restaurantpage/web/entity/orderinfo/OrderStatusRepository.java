package com.kiszka.restaurantpage.web.entity.orderinfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
    OrderStatus findById(int id);
}
