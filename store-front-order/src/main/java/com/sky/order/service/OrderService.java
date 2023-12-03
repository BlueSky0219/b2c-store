package com.sky.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.param.OrderParam;
import com.sky.param.PageParam;
import com.sky.pojo.Order;
import com.sky.utils.R;

/**
 * @author bluesky
 * @create 2022-11-22-11:10
 */
public interface OrderService extends IService<Order> {

    /**
     *  进行订单数据保存业务
     * @param orderParam
     * @return
     */
    R save(OrderParam orderParam);

    /**
     * 分组查询订单数据
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 检查订单是否有商品引用
     * @param productId
     * @return
     */
    R check(Integer productId);

    /**
     * 查询订单数据
     * @param pageParam
     * @return
     */
    R adminList(PageParam pageParam);
}
