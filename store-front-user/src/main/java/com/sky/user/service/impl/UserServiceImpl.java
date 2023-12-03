package com.sky.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.constants.UserConstants;
import com.sky.param.PageParam;
import com.sky.param.UserCheckParam;
import com.sky.param.UserLoginParam;
import com.sky.pojo.User;
import com.sky.user.mapper.UserMapper;
import com.sky.user.service.UserService;
import com.sky.utils.MD5Util;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-12-17:44
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public R check(UserCheckParam userCheckParam) {
        // 参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userCheckParam.getUserName());

        // 数据库查询
        Long total = userMapper.selectCount(queryWrapper);

        // 查询结果处理
        if (total == 0) {
            // 数据库中不存在，可用
            log.info("UserServiceImpl.check业务结束，结果:{}", "账号可以使用！");
            return R.ok("账号不存在，可以使用！");
        }

        log.info("UserServiceImpl.check业务结束，结果:{}", "账号不可使用！");
        return R.fail("账号已经存在，不可注册！");
    }

    /**
     * @param user
     * @return
     */
    @Override
    public R register(User user) {

        // 1.检查账号是否存在
        // 参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        // 数据库查询
        Long total = userMapper.selectCount(queryWrapper);

        if (total > 0) {
            log.info("UserServiceImpl.register业务结束，结果:{}", "账号不可使用！");
            return R.fail("账号已经存在，不可注册！");
        }

        // 2.密码加密处理
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        // 3.插入数据库
        int rows = userMapper.insert(user);

        // 4.返回结果封装
        if (rows == 0) {
            log.info("UserServiceImpl.register业务结束，结果:{}", "数据插入失败！注册失败！");
            return R.fail("注册失败！请稍后再试！");
        }

        log.info("UserServiceImpl.register业务结束，结果:{}", "注册成功！");
        return R.ok("注册成功！");

    }

    @Override
    public R login(UserLoginParam userLoginParam) {

        // 1.密码加盐处理
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);

        // 2.数据库查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userLoginParam.getUserName())
                .eq("password", newPwd);

        User user = userMapper.selectOne(queryWrapper);

        // 3.结果处理
        if (user == null) {
            log.info("UserServiceImpl.login业务结束，结果:{}", "账号密码错误！");
            return R.fail("账号密码错误！");
        }

        log.info("UserServiceImpl.login业务结束，结果:{}", "登录成功！");

        // 不返回password属性就会不返回此字段json！
        user.setPassword(null);
        user.setUserPhonenumber(null);
        return R.ok("登陆成功！", user);
    }

    @Override
    public R list(PageParam pageParam) {

        IPage<User> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());

        page = userMapper.selectPage(page, null);

        List<User> records = page.getRecords();
        long total = page.getTotal();

        R ok = R.ok("用户管理查询成功！", records, total);

        return ok;
    }

    @Override
    public R remove(Integer userId) {

        int rows = userMapper.deleteById(userId);
        log.info("UserServiceImpl.remove业务结束，结果:{}", rows);


        return R.ok("用户数据删除成功！");
    }

    @Override
    public R update(User user) {

        // 判断密码是否是原来的
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getUserId()).
                eq("password", user.getPassword());
        Long aLong = userMapper.selectCount(queryWrapper);

        if (aLong == 0) {
            // 明文需要加密
            user.setPassword(MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT));
        }

        int rows = userMapper.updateById(user);

        log.info("UserServiceImpl.remove业务结束，结果:{}", rows);

        return R.ok("用户信息修改成功！");
    }

}
