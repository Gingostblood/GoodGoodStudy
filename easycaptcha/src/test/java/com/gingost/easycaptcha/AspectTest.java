package com.gingost.easycaptcha;

import com.gingost.easycaptcha.service.impl.AspectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author:lezzy
 * @Date:2020/7/8 10:20
 */
@SpringBootTest
public class AspectTest {
    @Autowired
    private AspectService aspectService;

    @Test
    public void t1() throws InterruptedException {
        aspectService.testAspectOne();
        Thread.sleep(5000);
        aspectService.testAspectTwo();
        Thread.sleep(5000);
        aspectService.testAspectOh();
    }
}
