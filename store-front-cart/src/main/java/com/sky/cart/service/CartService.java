package com.sky.cart.service;

import com.sky.param.CartSaveParam;
import com.sky.pojo.Cart;
import com.sky.utils.R;

import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-21-15:16
 */
public interface CartService {

    /**
     * 购物车数据添加方法
     * @param cartSaveParam
     * @return
     */
    R save(CartSaveParam cartSaveParam);

    /**
     * 返回购物车数据
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 更新购物车业务
     * @param cart
     * @return
     */
    R update(Cart cart);

    /**
     * 删除购物车数据
     * @param cart
     * @return
     */
    R remove(Cart cart);

    /**
     * 清空对应id的购物车项
     * @param cartIds
     */
    void clearIds(List<Integer> cartIds);

    /**
     * 查询购物车项
     * @param productId
     * @return
     */
    R check(Integer productId);
}
