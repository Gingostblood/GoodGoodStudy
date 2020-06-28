package com.lezzy.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "order_info")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "orderInfo")
    @ToString.Exclude
    private Order order;

    @Column(name = "order_msg")
    private String orderMsg;
    private String uuid;

}
