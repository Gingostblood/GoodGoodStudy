package com.gingost.easycaptcha;

import cn.hutool.core.codec.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author:lezzy
 * @Date:2020/6/29 12:39
 */
@SpringBootTest
public class JwtTest {

    @Test
    public void t1(){
        System.out.println("======================"+ Base64.decodeStr("eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1ODk2NzY0OSwiaWF0IjoxNTU4OTQ2MDQ5fQ"));
    }
}
