package com.gingost.easycaptcha;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/6/29 12:39
 */
@SpringBootTest
public class JwtTest {
    @Value("${rsa.private_key}")
    private String key;

    @Test
    public void t1() {
        System.out.println("======================" + Base64.decodeStr("eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1ODk2NzY0OSwiaWF0IjoxNTU4OTQ2MDQ5fQ"));
    }

    @Test
    public void t2() {
        RSA rsa = new RSA(key, null);
        String pwd = new String(rsa.decrypt("123456", KeyType.PrivateKey));
        System.out.println(pwd);
    }

    @Test
    public void t3() {
        List<Boolean> list = Lists.newArrayList();
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        list.add(list1.contains(3));
        list.add(list1.contains(4));
        System.out.println(list.toString());
    }
}
