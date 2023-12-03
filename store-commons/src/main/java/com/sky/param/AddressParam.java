package com.sky.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sky.pojo.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2022-11-22-14:33
 */
@Data
public class AddressParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    private Address add;
}
