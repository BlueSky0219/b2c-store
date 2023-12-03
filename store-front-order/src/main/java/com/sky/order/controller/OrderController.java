package com.sky.order.controller;

import com.sky.order.service.OrderService;
import com.sky.param.CartListParam;
import com.sky.param.OrderParam;
import com.sky.param.PageParam;
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
 * @create 2022-11-22-11:06
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public R save(@RequestBody OrderParam orderParam){

        return orderService.save(orderParam);
    }

    @PostMapping("/list")
    public R list(@RequestBody @Validated CartListParam cartListParam, BindingResult result){

        if (result.hasErrors()) {
            return R.fail("参数异常，查询失败！");
        }

        return orderService.list(cartListParam.getUserId());
    }

    @PostMapping("/remove/check")
    public R save(@RequestBody Integer productId, BindingResult result){

        return orderService.check(productId);
    }

    @PostMapping("/remove/list")
    public R adminList(@RequestBody PageParam pageParam){

        return orderService.adminList(pageParam);
    }

}
