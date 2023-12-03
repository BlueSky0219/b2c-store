package com.sky.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sky.admin.mapper.AdminUserMapper;
import com.sky.admin.param.AdminUserParam;
import com.sky.admin.pojo.AdminUser;
import com.sky.admin.service.AdminUserService;
import com.sky.constants.UserConstants;
import com.sky.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bluesky
 * @create 2022-11-22-15:52
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(AdminUserParam adminUserParam) {

        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", adminUserParam.getUserAccount()).
                eq("user_password", MD5Util.encode(adminUserParam.getUserPassword() + UserConstants.USER_SLAT));

        AdminUser adminUser = adminUserMapper.selectOne(queryWrapper);
        log.info("AdminUserServiceImpl.login业务结束，结果:{}", adminUser);

        return adminUser;
    }
}
