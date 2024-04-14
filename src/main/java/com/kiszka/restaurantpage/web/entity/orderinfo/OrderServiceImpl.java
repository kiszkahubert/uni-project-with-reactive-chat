package com.kiszka.restaurantpage.web.entity.orderinfo;

import com.kiszka.restaurantpage.web.entity.validation.UserInfo;
import com.kiszka.restaurantpage.web.entity.validation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final UserService userService;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderStatusRepository orderStatusRepository, UserService userService){
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.userService = userService;
    }
    @Override
    public void saveOrder(OrderDetailsDto orderDetailsDto) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setItem(orderDetailsDto.getItem());
        orderDetails.setQuantity(orderDetailsDto.getQuantity());
        orderDetails.setTotalPrice(orderDetailsDto.getTotalPrice());
        orderDetails.setOrderDate(orderDetailsDto.getOrderDate());
        orderDetails.setDeliveryDate(orderDetailsDto.getDeliveryDate());
        orderDetails.setSauce(orderDetailsDto.getSauce());
        orderDetails.setMeat(orderDetailsDto.getMeat());
        orderDetails.setOrderStatus(orderStatusRepository.findById(1));
        UserInfo currentUser = userService.getCurrentUser();
        orderDetails.setUser(currentUser);
        orderRepository.save(orderDetails);
    }
    @Override
    public List<OrderDetailsDto> getOrdersForCurrentUser() {
        UserInfo currentUser = userService.getCurrentUser();
        List<OrderDetails> orders = orderRepository.findByUser(currentUser);
        return orders.stream().map(order -> new OrderDetailsDto(
                order.getItem(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getSauce(),
                order.getMeat()
        )).collect(Collectors.toList());
    }
}
