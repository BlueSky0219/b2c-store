package com.sky.admin.service.impl;

import com.sky.admin.service.OrderService;
import com.sky.clients.OrderClient;
import com.sky.param.PageParam;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bluesky
 * @create 2022-11-24-17:42
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderClient orderClient;

    @Override
    public R list(PageParam pageParam) {

        R r = orderClient.list(pageParam);
        log.info("OrderServiceImpl.list业务结束，结果:{}", r);

        return r;
    }
}
