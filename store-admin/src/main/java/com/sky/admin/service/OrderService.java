package com.sky.admin.service;

import com.sky.param.PageParam;
import com.sky.utils.R;

/**
 * @author bluesky
 * @create 2022-11-24-17:42
 */
public interface OrderService {

    /**
     * 订单业务实现类
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);
}
