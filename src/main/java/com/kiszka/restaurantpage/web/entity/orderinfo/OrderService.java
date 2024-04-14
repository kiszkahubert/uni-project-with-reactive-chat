package com.kiszka.restaurantpage.web.entity.orderinfo;


import java.util.List;

public interface OrderService {
    void saveOrder(OrderDetailsDto orderDetailsDto);
    List<OrderDetailsDto> getOrdersForCurrentUser();
}
