package com.sky.param;

import com.sky.pojo.Product;
import lombok.Data;

/**
 * @author bluesky
 * @create 2022-11-23-18:37
 */
@Data
public class ProductSaveParam extends Product {

    /**
     * 保存商品详情的图片地址！图片之间使用 + 拼接
     */
    private String pictures;
}
