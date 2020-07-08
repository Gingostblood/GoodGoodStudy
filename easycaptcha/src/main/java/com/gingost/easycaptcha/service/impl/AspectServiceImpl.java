package com.gingost.easycaptcha.service.impl;

import com.gingost.easycaptcha.annotation.Broadcast;
import org.springframework.stereotype.Service;

/**
 * @author:lezzy
 * @Date:2020/7/8 10:12
 */
@Service
public class AspectServiceImpl implements AspectService {

    @Override
    @Broadcast("one")
    public void testAspectOne() {
        System.out.println("这是one");
    }

    @Override
    @Broadcast("two")
    public void testAspectTwo() {
        System.out.println("这是two");
    }

    @Override
    @Broadcast
    public void testAspectOh() {
        System.out.println("这是other");
    }
}
