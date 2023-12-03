package com.sky.collect.service;

import com.sky.pojo.Collect;
import com.sky.utils.R;

/**
 * @author bluesky
 * @create 2022-11-21-11:57
 */
public interface CollectService {

    /**
     * 收藏添加的方法
     *
     * @param collect
     * @return
     */
    R save(Collect collect);

    /**
     * 根据用户id查询商品信息集合
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 根据用户id和商品id删除收藏数据
     * @param collect
     * @return
     */
    R remove(Collect collect);

    /**
     * 删除根据商品id
     * @param productId
     * @return
     */
    R removeByPid(Integer productId);
}
