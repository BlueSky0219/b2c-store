package com.sky.product.controller;

import com.sky.param.ProductCollectParam;
import com.sky.param.ProductIdParam;
import com.sky.pojo.Product;
import com.sky.product.service.ProductService;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-21-15:22
 */
@RestController
@RequestMapping("/product")
public class ProductCartController {

    @Autowired
    private ProductService productService;

    @PostMapping("/cart/detail")
    public Product cdetail(@RequestBody @Validated ProductIdParam productIdParam, BindingResult result) {

        if (result.hasErrors()) {
            return null;
        }

        R r = productService.detail(productIdParam.getProductID());
        Product product = (Product) r.getData();

        return product;
    }

    @PostMapping("/cart/list")
    public List<Product> cartList(@RequestBody @Validated ProductCollectParam productCollectParam, BindingResult result) {

        if (result.hasErrors()) {
            return new ArrayList<>();
        }

        return productService.cartList(productCollectParam.getProductIds());
    }
}
