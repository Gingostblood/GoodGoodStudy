package com.lezzy.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "tb_book")
@Getter
@Setter
@Accessors(chain = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_name")
    private String bookName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private Author author;
}
