package com.gingost.easycaptcha.controller;

import com.gingost.easycaptcha.annotation.Admin;
import com.gingost.easycaptcha.common.JsonResult;
import com.gingost.easycaptcha.domain.Dto.AuthUserDto;
import com.gingost.easycaptcha.utli.RedisUtils;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author:lezzy
 * @Date:2020/6/24 9:18
 */
@Controller
@RequestMapping("/")
@AllArgsConstructor
public class AllController {
    private RedisUtils redisUtils;

    @RequestMapping("one")
    @Admin
    public String page(){
        return "hello";
    }

    @ResponseBody
    @GetMapping("sql")
    public String sql(){
        return "sql";
    }

    @ResponseBody
    @Admin
    @RequestMapping("/captcha")
    public ResponseEntity captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        captcha.setLen(2);
        //获取运算结果
        String result=captcha.text();
        String uuid= UUID.randomUUID().toString();
        redisUtils.set(uuid,result,60);
        Map<String,Object> map=new HashMap<String,Object>(2){{
            put("uuid",uuid);
            put("img",captcha.toBase64());
        }};
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @RequestMapping("login")
    @ResponseBody
    public JsonResult login(@Validated AuthUserDto authUserDto){
        if (redisUtils.get(authUserDto.getUuid())==null){
            throw new RuntimeException("验证码已过期");
        }
        if (!redisUtils.get(authUserDto.getUuid()).equals(authUserDto.getCode())){
            throw new RuntimeException("验证码错误");
        }
        if (redisUtils.get(authUserDto.getUuid()).equals(authUserDto.getCode())){
            return new JsonResult("登录成功");
        }
        return null;
    }
}
