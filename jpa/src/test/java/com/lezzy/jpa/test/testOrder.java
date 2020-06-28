package com.lezzy.jpa.test;

import com.lezzy.jpa.entity.Order;
import com.lezzy.jpa.entity.OrderInfo;
import com.lezzy.jpa.jpa.OrderJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class testOrder {
    @Autowired
    private OrderJPA orderJPA;
    @Test
    public void testOneToOne(){
        /*OrderInfo orderInfo=new OrderInfo();
        orderInfo.setOrderMsg("iphone 11 pro max").setUuid(UUID.randomUUID().toString());
        Order order=new Order();
        order.setStartTime(new Date()).setEndTime(new Date()).setOrderInfo(orderInfo);
        orderJPA.saveAndFlush(order);*/
        Optional<Order> order = orderJPA.findById(1);
        System.out.println(order);
        System.out.println(order.get().getOrderInfo());
    }
}
