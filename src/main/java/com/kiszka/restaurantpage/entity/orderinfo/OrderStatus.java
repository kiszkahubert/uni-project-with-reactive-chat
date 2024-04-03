package com.kiszka.restaurantpage.entity.orderinfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity @Table(name = "orderstatus")
public class OrderStatus {
    @Id
    @Column(name="statuskey")
    private int id;
    @Column(name="status")
    private String status;
}
