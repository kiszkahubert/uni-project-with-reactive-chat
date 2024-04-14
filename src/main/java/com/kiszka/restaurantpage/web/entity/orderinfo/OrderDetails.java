package com.kiszka.restaurantpage.web.entity.orderinfo;

import com.kiszka.restaurantpage.web.entity.validation.UserInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity @Table(name="orders")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_id_order")
    @SequenceGenerator(name = "seq_id_order",sequenceName = "seq_id_order",allocationSize = 1)
    @Column(name="orderkey")
    private int id;
    @ManyToOne
    @JoinColumn(name="userkey",nullable = false)
    private UserInfo user;
    @Column(name="item")
    private String item;
    @Column(name="quantity")
    private int quantity;
    @Column(name="totalprice")
    private int totalPrice;
    @Column(name="orderdate")
    private Date orderDate;
    @Column(name="deliverydate")
    private Date deliveryDate;
    @Column(name="sauce")
    private String sauce;
    @Column(name="meat")
    private String meat;
    @ManyToOne
    @JoinColumn(name="statuskey", nullable = false)
    private OrderStatus orderStatus;
}
