package com.sky.user.service;

import com.sky.param.PageParam;
import com.sky.param.UserCheckParam;
import com.sky.param.UserLoginParam;
import com.sky.pojo.User;
import com.sky.utils.R;

/**
 * @author bluesky
 * @create 2022-11-12-17:42
 */
public interface UserService {

    /**
     * 检查账号是否可用业务
     *
     * @param userCheckParam 账号参数 已经校验完毕
     * @return 检查结果 001 004
     */
    R check(UserCheckParam userCheckParam);

    /**
     * 参数已经校验，但是密码是明文！
     *
     * @param user
     * @return
     */
    R register(User user);

    /**
     * 登陆业务
     *
     * @param userLoginParam 账号和密码 已经登录 但是密码是明文
     * @return 结果 001 004
     */
    R login(UserLoginParam userLoginParam);

    /**
     * 后台管理调用，查询全部用户数据
     *
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);

    /**
     * 根据用户id删除用户
     *
     * @param userId
     * @return
     */
    R remove(Integer userId);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    R update(User user);

}
