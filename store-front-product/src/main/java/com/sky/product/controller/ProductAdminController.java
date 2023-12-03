package com.sky.product.controller;

import com.sky.param.ProductSaveParam;
import com.sky.pojo.Product;
import com.sky.product.service.ProductService;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author bluesky
 * @create 2022-11-23-16:30
 */
@RestController
@RequestMapping("/product")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/count")
    public Long adminCount(@RequestBody Integer categoryId) {

        return productService.adminCount(categoryId);
    }

    @PostMapping("/admin/update")
    public R adminUpdate(@RequestBody Product product) {

        return productService.adminUpdate(product);
    }

    @PostMapping("/admin/remove")
    public R adminRemove(@RequestBody Integer productId) {

        return productService.adminRemove(productId);
    }



    @PostMapping("/admin/list")
    public R adminList(@RequestBody Integer userId,  @RequestParam("userRole") Integer userRole) {

        return productService.adminList(userId,userRole);
    }

    @PostMapping("/admin/save")
    public R adminSave(@RequestBody ProductSaveParam productSaveParam,@RequestParam("adminId")Integer adminId) {

        return productService.adminSave(productSaveParam,adminId);
    }

}
