package com.gingost.easycaptcha.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:lezzy
 * @Date:2020/6/24 11:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    private int code = 0;
    private String msg;
    private Object data;

    public JsonResult(RuntimeException e) {
        this.code = 1;
        this.msg = e.getMessage();
    }

    public JsonResult(String msg) {
        this.msg = msg;
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(Object data, String msg) {
        this.data = data;
        this.msg = msg;
    }
}
