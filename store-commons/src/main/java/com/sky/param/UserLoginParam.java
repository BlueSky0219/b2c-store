package com.sky.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author bluesky
 * @create 2022-11-12-18:41
 */
@Data
public class UserLoginParam {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
