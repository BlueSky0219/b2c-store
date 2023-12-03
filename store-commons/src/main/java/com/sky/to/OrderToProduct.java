package com.sky.to;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bluesky
 * @create 2022-11-22-11:02
 */
@Data
public class OrderToProduct implements Serializable {

    public static final Long serialVersionUID = 1L;

    private Integer productId;
    private Integer num;
}
