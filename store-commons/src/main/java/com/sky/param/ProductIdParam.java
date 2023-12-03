package com.sky.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2022-11-16-14:37
 */
@Data
public class ProductIdParam {

    @NotNull
    private Integer productID;
}
