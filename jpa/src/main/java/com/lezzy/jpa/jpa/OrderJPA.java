package com.lezzy.jpa.jpa;

import com.lezzy.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPA extends JpaRepository<Order,Integer> {
}
