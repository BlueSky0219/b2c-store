package com.sky.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-21-12:21
 */
@Data
public class ProductCollectParam {

    @NotEmpty
    private List<Integer> productIds;
}
