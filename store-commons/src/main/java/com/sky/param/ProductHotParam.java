package com.sky.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-15-16:55
 */
@Data
public class ProductHotParam {

    @NotEmpty
    private List<String> categoryName;
}
