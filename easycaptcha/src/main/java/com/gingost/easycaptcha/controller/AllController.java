package com.gingost.easycaptcha.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.gingost.easycaptcha.annotation.Admin;
import com.gingost.easycaptcha.common.JsonResult;
import com.gingost.easycaptcha.config.PropertiesConfig;
import com.gingost.easycaptcha.domain.Dto.AuthUserDto;
import com.gingost.easycaptcha.domain.User;
import com.gingost.easycaptcha.security.TokenProvider;
import com.gingost.easycaptcha.service.impl.OnlineUserService;
import com.gingost.easycaptcha.utli.RedisUtils;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.plugin.liveconnect.SecurityContextHelper;

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
public class AllController {
    private RedisUtils redisUtils;
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private OnlineUserService onlineUserService;
    private TokenProvider tokenProvider;
    private PropertiesConfig propertiesConfig;

    @Value("${rsa.private_key}")
    private String private_key;

    public AllController(RedisUtils redisUtils, AuthenticationManagerBuilder authenticationManagerBuilder, OnlineUserService onlineUserService, TokenProvider tokenProvider, PropertiesConfig propertiesConfig) {
        this.redisUtils = redisUtils;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.onlineUserService = onlineUserService;
        this.tokenProvider = tokenProvider;
        this.propertiesConfig = propertiesConfig;
    }

    @GetMapping("one")
    @Admin
    public String page() {
        return "hello";
    }

    @ResponseBody
    @GetMapping("sql")
    @PreAuthorize("hasRole('ROLE_CONTRO')")
    public String sql() {
        return "sql";
    }

    @ResponseBody
    @Admin
    @RequestMapping("/captcha")
    public ResponseEntity captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        captcha.setLen(2);
        //获取运算结果
        String result = captcha.text();
        String uuid = UUID.randomUUID().toString();
        redisUtils.set(uuid, result, 60);
        Map<String, Object> map = new HashMap<String, Object>(2) {{
            put("uuid", uuid);
            //put("img", captcha.toBase64());
            put("res",result);
        }};
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @PostMapping("login")
    @ResponseBody
    @Admin
    public JsonResult login(@Validated AuthUserDto authUserDto) {
//        RSA rsa=new RSA(private_key,null);
//        String pwd=new String(rsa.decrypt(authUserDto.getPassword(), KeyType.PrivateKey));
        String code = (String) redisUtils.get(authUserDto.getUuid());
        try {
            redisUtils.del(authUserDto.getUuid());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("验证码已经过期");
        }
        if (authUserDto.getCode() == null || !code.equals(authUserDto.getCode())) {
            throw new RuntimeException("验证码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUserDto.getUsername(), authUserDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String token = tokenProvider.createToken(authentication);
        onlineUserService.save(user, token);
        Map<String, Object> map = new HashMap(2) {{
            put("token", propertiesConfig.getTokenStartWith() + token);
            put("user", user);
        }};
        System.out.println("======================" + (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //System.out.println("======================"+ Base64.decodeStr("eyJzdWIiOiJhZG1pbiIsImF1dGgiOiLns7vnu5_nrqHnkIblkZgiLCJleHAiOjE1OTM0MTk4NzB9"));
        return new JsonResult(map, "登录成功");
    }
}
