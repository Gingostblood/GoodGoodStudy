package com.gingost.easycaptcha.domain.Dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author:lezzy
 * @Date:2020/6/24 10:48
 */
@Data
public class AuthUserDto {
    @NotBlank(message = "用户名不能为空")
    private String username;


    private String password;

    @NotBlank(message = "验证码不能为空")
    private String code;

    //验证码标识
    private String uuid;
}
