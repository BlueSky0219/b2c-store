package com.sky.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sky.vo.CartVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-22-10:57
 */
@Data
public class OrderParam implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;


}
