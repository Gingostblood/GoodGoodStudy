package com.lezzy.jpa.jpa;

import com.lezzy.jpa.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorJPA extends JpaRepository<Author, Integer> {
}
