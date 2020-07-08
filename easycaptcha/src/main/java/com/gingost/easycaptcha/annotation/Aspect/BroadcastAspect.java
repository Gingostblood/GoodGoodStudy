package com.gingost.easycaptcha.annotation.Aspect;

import com.gingost.easycaptcha.annotation.Broadcast;
import com.gingost.easycaptcha.utli.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author:lezzy
 * @Date:2020/7/8 9:44
 */
@Component
@Aspect
public class BroadcastAspect {
    @Autowired
    private RedisUtils redisUtils;

    @Value("${keys.one-key}")
    private String onekey;

    @Value("${keys.two-key}")
    private String towkey;

    @Pointcut("@annotation(com.gingost.easycaptcha.annotation.Broadcast)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void start(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("======开始织入");
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Broadcast broadcast = signature.getMethod().getDeclaredAnnotation(Broadcast.class);
        if (broadcast != null) {
            doHanderMain(proceedingJoinPoint, broadcast);
        }

    }

    private void doHanderMain(ProceedingJoinPoint proceedingJoinPoint, Broadcast broadcast){
        if (broadcast.value().equals("one")) {
            redisUtils.set(onekey, UUID.randomUUID().toString().toLowerCase(), 60, TimeUnit.SECONDS);
            System.out.println("======onekey-redis");
        } else if (broadcast.value().equals("two")) {
            redisUtils.set(towkey,"lizhenyuwoaini",60,TimeUnit.SECONDS);
            System.out.println("======twokey-redis");
        }else {
            System.out.println("=========输入参数不对嗷");
        }
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            System.out.println("启动自毁程序");
        }
    }
}
