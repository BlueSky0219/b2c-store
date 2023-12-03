package com.sky.admin.service.impl;

import com.sky.admin.service.UserService;
import com.sky.clients.UserClient;
import com.sky.param.CartListParam;
import com.sky.param.PageParam;
import com.sky.pojo.User;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author bluesky
 * @create 2022-11-22-17:21
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserClient userClient;

    @Cacheable(value = "list.user", key = "#pageParam.currentPage+" + '-' + "#pageParam.pageSize")
    @Override
    public R userList(PageParam pageParam) {

        log.info("UserServiceImpl.userList业务开始，参数:{}", pageParam);
        R r = userClient.listPage(pageParam);
        log.info("UserServiceImpl.userList业务结束，参数:{}", r);

        return r;
    }

    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R remove(CartListParam cartListParam) {

        R r = userClient.remove(cartListParam);
        log.info("UserServiceImpl.remove业务结束，参数:{}", r);

        return r;
    }

    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R update(User user) {

        R r = userClient.update(user);
        log.info("UserServiceImpl.update业务结束，参数:{}", r);


        return r;
    }

    @Override
    public R save(User user) {

        R r = userClient.save(user);
        log.info("UserServiceImpl.save业务结束，参数:{}", r);

        return r;
    }
}
