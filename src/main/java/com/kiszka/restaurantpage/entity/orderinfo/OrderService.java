package com.kiszka.restaurantpage.entity.orderinfo;


import java.util.List;

public interface OrderService {
    void saveOrder(OrderDetailsDto orderDetailsDto);
    List<OrderDetailsDto> getOrdersForCurrentUser();
}
