package com.sky.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author bluesky
 * @create 2022-11-14-17:17
 */
@Data
public class ProductPromoParam {

    @NotBlank
    private String categoryName;
}
