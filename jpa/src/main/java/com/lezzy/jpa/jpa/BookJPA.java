package com.lezzy.jpa.jpa;

import com.lezzy.jpa.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJPA extends JpaRepository<Book,Integer> {
}
