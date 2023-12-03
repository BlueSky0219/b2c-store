package com.sky.admin.controller;

import com.sky.admin.service.UserService;
import com.sky.param.CartListParam;
import com.sky.param.PageParam;
import com.sky.pojo.User;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bluesky
 * @create 2022-11-22-17:18
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public R userList(PageParam pageParam){

        return  userService.userList(pageParam);
    }

    @PostMapping("/remove")
    public R remove(CartListParam cartListParam){

        return  userService.remove(cartListParam);
    }

    @PostMapping("/update")
    public R update(User user){

        return  userService.update(user);
    }

    @PostMapping("/save")
    public R save(User user){

        return  userService.save(user);
    }
}
