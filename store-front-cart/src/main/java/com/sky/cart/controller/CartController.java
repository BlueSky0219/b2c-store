package com.sky.cart.controller;

import com.sky.cart.service.CartService;
import com.sky.param.CartListParam;
import com.sky.param.CartSaveParam;
import com.sky.pojo.Cart;
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
 * @create 2022-11-21-15:15
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/save")
    public R save(@RequestBody @Validated CartSaveParam cartSaveParam, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("核心参数为null，添加失败！");
        }

        return cartService.save(cartSaveParam);
    }

    @PostMapping("/list")
    public R list(@RequestBody @Validated CartListParam cartListParam,BindingResult result){
        if (result.hasErrors()) {
              return R.fail("购物车数据查询失败！");
        }

        return cartService.list(cartListParam.getUserId());
    }

    @PostMapping("/update")
    public R update(@RequestBody  Cart cart){


        return cartService.update(cart);
    }

    @PostMapping("/remove")
    public R remove(@RequestBody  Cart cart){


        return cartService.remove(cart);
    }

    @PostMapping("/remove/check")
    public R removeClick(@RequestBody Integer productId){

        return cartService.check(productId);
    }
}
