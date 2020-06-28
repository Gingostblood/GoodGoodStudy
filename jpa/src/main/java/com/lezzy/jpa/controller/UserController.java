package com.lezzy.jpa.controller;

import com.lezzy.jpa.entity.User;
import com.lezzy.jpa.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserJPA userJPA;

    @RequestMapping("/findall")
    public List<User> findAll() {
        return userJPA.findAll();
    }

    @RequestMapping("/add")
    public User addUser() {
        User user = new User();
        user.setName("张森云").setAge(16).setAddress("宜宾市");
        //.save的作用是更新和增加，如果设置了主键则是更新，没有设置主键则是增加
        User ur = userJPA.save(user);
        System.out.println(ur);
        return ur;

    }

    @RequestMapping("/deletebyid")
    public List<User> deleteOne() {
        Long id = 1L;
        userJPA.deleteById(id);
        return userJPA.findAll();
    }

    @RequestMapping("/deleteByUser")
    public List<User> deleteUser(Long id) {
        User user = new User();
        user.setId(id);
        userJPA.delete(user);
        return userJPA.findAll();
    }

    @RequestMapping("/test")
    public String gotest() {
        return "nmsl";
    }

    @RequestMapping("/findOne")
    public User findOne(String name) {
        return userJPA.findByName(name);
    }
}
