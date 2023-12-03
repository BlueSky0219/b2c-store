package com.sky.product.controller;

import com.sky.param.ProductCollectParam;
import com.sky.product.service.ProductService;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bluesky
 * @create 2022-11-21-12:24
 */
@RestController
@RequestMapping("/product")
public class ProductCollectController {

    @Autowired
    private ProductService productService;

    @PostMapping("/collect/list")
    public R productIds(@RequestBody @Validated ProductCollectParam productCollectParam, BindingResult result){

        if(result.hasErrors()){
            return R.ok("没有收藏数据！");
        }

        return productService.ids(productCollectParam.getProductIds());

    }
}
