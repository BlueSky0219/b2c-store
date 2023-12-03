package com.sky.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author bluesky
 * @create 2022-11-11-12:07
 * TODO: 要使用jsr 303的注解 进行参数校验！
 * @NotBlank 字符串 不能为null 和 空字符串 “”
 * @NotNull 字符串 不能为null
 * @NotEmpt 集合类型 集合长度不能为0
 */
@Data
public class UserCheckParam {

    @NotBlank
    private String userName; // 注意：参数名称要等于前端传递的JSON Key的名称


}
