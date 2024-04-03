package com.kiszka.restaurantpage.entity.validation;

import com.kiszka.restaurantpage.entity.orderinfo.OrderDetails;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usercredentials")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_id")
    @SequenceGenerator(name = "seq_id",sequenceName = "seq_id",allocationSize = 1)
    @Column(name = "userkey")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @OneToMany(mappedBy = "user")
    private List<OrderDetails> orders;
}
