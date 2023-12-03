package com.sky.param;

/**
 * @author bluesky
 * @create 2022-11-14-14:46
 */

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressRemoveParam {

    @NotNull
    private Integer id;
}
