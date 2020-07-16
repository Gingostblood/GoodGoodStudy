package com.lezzy.jpa.test;

import com.lezzy.jpa.entity.Author;
import com.lezzy.jpa.entity.Book;
import com.lezzy.jpa.jpa.AuthorJPA;
import com.lezzy.jpa.jpa.BookJPA;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class testBook {
    @Autowired
    private BookJPA bookJPA;
    @Autowired
    private AuthorJPA authorJPA;

    @Test
    @Transactional
    @Rollback(false)
    public void testBook() {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Author author1 = new Author();
        Author author2 = new Author();
        author1.setAuthorName("韩寒");
        author2.setAuthorName("S");
        book1.setBookName("平凡世界").setAuthor(author1);
        book2.setBookName("杯中窥人").setAuthor(author1);
        book3.setBookName("Thinking in Java").setAuthor(author2);
        bookJPA.saveAndFlush(book1);
        bookJPA.saveAndFlush(book2);
        bookJPA.saveAndFlush(book3);
    }

    @Test
    @Transactional
    @Rollback(false)
    /*
    相比上面的方法，这里将级联控制方向反转。
     */
    public void testAuthor() {
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        book1.setBookName("西游记");
        book2.setBookName("水浒传");
        book3.setBookName("雷雨");
        Author author1 = new Author();
        Author author2 = new Author();
        author1.setAuthorName("李振宇").getBooks().add(book1);
        //设置外键
        book1.setAuthor(author1);
        book2.setAuthor(author1);
        book3.setAuthor(author2);
        author1.getBooks().add(book2);
        author2.setAuthorName("郭沫若").getBooks().add(book3);
        authorJPA.saveAndFlush(author1);
        authorJPA.saveAndFlush(author2);
    }

    @Test
    public void testDelete() {
        authorJPA.deleteById(2);
    }

    @Test
    public void testString() {
        List<String> roles = new ArrayList<>();
        roles.add("管理员，admin");
        roles.add("管理员");
        System.out.println();
    }
}
