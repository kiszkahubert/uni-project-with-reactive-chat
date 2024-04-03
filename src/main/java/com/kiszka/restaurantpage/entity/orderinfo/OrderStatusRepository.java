package com.kiszka.restaurantpage.entity.orderinfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
    OrderStatus findById(int id);
}
