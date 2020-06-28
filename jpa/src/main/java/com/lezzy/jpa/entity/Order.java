package com.lezzy.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_order")
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @ToString.Exclude
    private OrderInfo orderInfo;


    @Column(name ="start_time" )
    private Date startTime;
    @Column(name ="end_time" )
    private Date endTime;
}
