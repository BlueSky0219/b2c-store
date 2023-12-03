package com.sky.admin.service;

import com.sky.admin.param.AdminUserParam;
import com.sky.admin.pojo.AdminUser;
import com.sky.param.PageParam;
import com.sky.utils.R;

/**
 * @author bluesky
 * @create 2022-11-22-15:52
 */
public interface AdminUserService {



    /**
     * 登录业务方法
     *
     * @param adminUserParam
     * @return
     */
    AdminUser login(AdminUserParam adminUserParam);

}
