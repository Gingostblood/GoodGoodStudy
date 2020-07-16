package com.gingost.security.controller;

import com.gingost.security.domain.DateVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/test")
    public String fristTest() {
        return "hello";
    }

    @RequestMapping("/date")
    public DateVo testJson() {
        DateVo dateVo = new DateVo();
        dateVo.setDate(new Date());
        return dateVo;
    }
}
