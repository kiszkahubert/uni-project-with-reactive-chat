package com.kiszka.restaurantpage.entity.orderinfo;

import com.kiszka.restaurantpage.entity.validation.UserInfo;

public interface OrderService {
    void saveOrder(OrderDetailsDto orderDetailsDto);
}
