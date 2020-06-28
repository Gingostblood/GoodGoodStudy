package com.gingost.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String pwd = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String newPwd=bCryptPasswordEncoder.encode(pwd);
        System.out.println("==============================" + newPwd);
        auth.inMemoryAuthentication().withUser("lizhenyu").roles("user").password("{noop}123").
                and().withUser("fku").roles("user").password(newPwd);
        //super.configure(auth);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user").permitAll();
    }
}
