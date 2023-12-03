package com.sky.admin.service;

import com.sky.param.CartListParam;
import com.sky.param.PageParam;
import com.sky.pojo.User;
import com.sky.utils.R;

/**
 * @author bluesky
 * @create 2022-11-22-17:21
 */
public interface UserService {

    /**
     * 用户展示
     * @param pageParam
     * @return
     */
    R userList(PageParam pageParam);

    /**
     * 删除用户数据
     * @param cartListParam
     * @return
     */
    R remove(CartListParam cartListParam);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    R update(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    R save(User user);
}
