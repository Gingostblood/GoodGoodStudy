package com.gingost.easycaptcha.service.impl;

import com.gingost.easycaptcha.domain.User;
import com.gingost.easycaptcha.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author:lezzy
 * @Date:2020/6/28 11:35
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(s);
        if (user == null) {
            throw new RuntimeException("未找到该用户");
        }
        return user;
    }
}
