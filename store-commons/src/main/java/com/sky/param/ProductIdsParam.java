package com.sky.param;

import com.sky.pojo.Product;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-16-13:04
 */
@Data
public class ProductIdsParam extends PageParam{

    @NotNull
    private List<Integer> categoryID;


}
