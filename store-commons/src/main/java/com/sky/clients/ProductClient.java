package com.sky.clients;

import com.sky.param.ProductCollectParam;
import com.sky.param.ProductIdParam;
import com.sky.param.ProductSaveParam;
import com.sky.pojo.Admin;
import com.sky.pojo.Product;
import com.sky.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-16-16:23
 */
@FeignClient("product-service")
public interface ProductClient {

    /**
     * 搜索服务调用，进行全部数据查询，用于搜索数据库同步数据！
     *
     * @return
     */
    @GetMapping("/product/list")
    List<Product> allList();

    @PostMapping("/product/collect/list")
    R productIds(@RequestBody ProductCollectParam productCollectParam);

    @PostMapping("/product/cart/detail")
    Product productDetail(@RequestBody ProductIdParam productIdParam);

    @PostMapping("/product/cart/list")
    List<Product> cartList(@RequestBody ProductCollectParam productCollectParam);

    @PostMapping("/product/admin/count")
    Long adminCount(@RequestBody Integer categoryId);

    @PostMapping("/product/admin/save")
    R adminCount(@RequestBody ProductSaveParam productSaveParam,@RequestParam("adminId")Integer adminId);

    @PostMapping("/product/admin/update")
    R adminUpdate(@RequestBody Product product);

    @PostMapping("/product/admin/remove")
    R adminRemove(@RequestBody Integer product);


    @PostMapping("/product/admin/list")
    R adminList(@RequestBody Integer userId, @RequestParam("userRole") Integer userRole);

    @PostMapping("/product/admin/saveProduct")
    R adminSave(@RequestBody ProductSaveParam productSaveParam, @RequestParam("userId") Integer userId);
}
