package com.sky.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sky.carousel.mapper.CarouselMapper;
import com.sky.carousel.service.CarouselService;
import com.sky.pojo.Carousel;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bluesky
 * @create 2022-11-14-15:42
 */
@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 查找优先级最高的六条轮播图数据
     *
     * @return
     */
    @Cacheable(value = "list.carousel", key ="#root.methodName",cacheManager = "cacheManagerDay")
    @Override
    public R list() {

        // 1.按照优先级查询数据库数据
        QueryWrapper<Carousel> carouselQueryWrapper = new QueryWrapper<>();
        carouselQueryWrapper.orderByDesc("priority");

        List<Carousel> list = carouselMapper.selectList(carouselQueryWrapper);

        // 2.我们使用stream进行数据切割，保留6条数据
        List<Carousel> collect = list.stream().limit(6).collect(Collectors.toList());

        R ok = R.ok(collect);

        log.info("CarouselServiceImpl.list业务结束，结果:{}", ok);

        return ok;
    }
}
