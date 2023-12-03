package com.sky.carousel.controller;

import com.sky.carousel.service.CarouselService;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bluesky
 * @create 2022-11-14-15:38
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    /**
     * 查询轮播图数据，只要优先级最高的六条
     * @return
     */
    @PostMapping("/list")
    public R list(){

        return carouselService.list();
    }
}
