package com.sky.admin.controller;

import com.sky.admin.param.AdminUserParam;
import com.sky.admin.pojo.AdminUser;
import com.sky.admin.service.AdminUserService;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * @author bluesky
 * @create 2022-11-22-15:48
 */
@RestController
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/user/login")
    public R login(@Validated AdminUserParam adminUserParam, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return R.fail("核心参数为null，登陆失败！");
        }

        // 验证码校验
        String captcha = (String) session.getAttribute("captcha");

        // equalsIgnoreCase()忽略大小写比较
        if (!adminUserParam.getVerCode().equalsIgnoreCase(captcha)) {

            return R.fail("验证码错误！");
        }

        AdminUser adminUser = adminUserService.login(adminUserParam);

        if (adminUser == null) {
            return R.fail("登陆失败！账号密码错误！");
        }

        session.setAttribute("userInfo",adminUser);
        session.setAttribute("userId",adminUser.getUserId());
        session.setAttribute("userRole",adminUser.getUserRole());

        return R.ok("登陆成功");
    }

    @GetMapping("/user/logout")
    public R logout(HttpSession session){

        // 清空session
        session.invalidate();

        return R.ok("退出登录成功！");
    }
}
