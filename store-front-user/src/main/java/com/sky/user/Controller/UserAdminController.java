package com.sky.user.Controller;

import com.sky.param.CartListParam;
import com.sky.param.PageParam;
import com.sky.pojo.User;
import com.sky.user.service.UserService;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bluesky
 * @create 2022-11-22-17:02
 */
@RestController
@RequestMapping("/user")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/admin/list")
    public R listPage(@RequestBody PageParam pageParam){

        return userService.list(pageParam);
    }

    @PostMapping("/admin/remove")
    public R remove(@RequestBody CartListParam cartListParam){

        return userService.remove(cartListParam.getUserId());
    }

    @PostMapping("/admin/update")
    public R update(@RequestBody User user){

        return userService.update(user);
    }

    @PostMapping("/admin/save")
    public R save(@RequestBody User user){

        return userService.register(user);
    }

}
