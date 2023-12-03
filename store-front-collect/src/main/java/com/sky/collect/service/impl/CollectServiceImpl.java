package com.sky.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sky.clients.ProductClient;
import com.sky.collect.mapper.CollectMapper;
import com.sky.collect.service.CollectService;
import com.sky.param.ProductCollectParam;
import com.sky.pojo.Collect;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-21-11:57
 */
@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CollectMapper collectMapper;

    @Override
    public R save(Collect collect) {

        // 1.先查询是否存在
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", collect.getUserId()).eq("product_id", collect.getProductId());

        Long count = collectMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("收藏已经添加，无需二次添加！");
        }

        // 2.不存在进行添加
        // 补充下时间
        collect.setCollectTime(System.currentTimeMillis());
        int rows = collectMapper.insert(collect);
        log.info("CollectServiceImpl.save业务结束，结果:{}", rows);

        return R.ok("收藏添加成功！");
    }

    @Override
    public R list(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).select("product_id");

        List<Object> idsObject = collectMapper.selectObjs(queryWrapper);
        ProductCollectParam productCollectParam = new ProductCollectParam();

        List<Integer> ids = new ArrayList<>();
        for (Object o : idsObject) {
            ids.add((Integer) o);
        }

        productCollectParam.setProductIds(ids);

        R r = productClient.productIds(productCollectParam);
        log.info("CollectServiceImpl.list业务结束，结果:{}", r);

        return r;
    }

    @Override
    public R remove(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", collect.getUserId()).eq("product_id", collect.getProductId());

        int rows = collectMapper.delete(queryWrapper);
        log.info("CollectServiceImpl.remove业务结束，结果:{}", rows);

        return R.ok("收藏移出成功！");
    }

    @Override
    public R removeByPid(Integer productId) {

        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);

        int rows = collectMapper.delete(queryWrapper);
        log.info("CollectServiceImpl.removeByPid业务结束，结果:{}", rows);

        return R.ok("收藏移出成功！");

    }
}
