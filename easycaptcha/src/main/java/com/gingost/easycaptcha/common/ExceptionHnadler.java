package com.gingost.easycaptcha.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author:lezzy
 * @Date:2020/6/24 11:26
 */

@RestControllerAdvice
public class ExceptionHnadler {
    @ExceptionHandler(RuntimeException.class)
    public JsonResult doHandlerException(RuntimeException e){
        e.printStackTrace();
        return new JsonResult(e);
    }
}
