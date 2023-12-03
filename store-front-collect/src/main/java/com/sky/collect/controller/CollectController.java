package com.sky.collect.controller;

import com.sky.collect.service.CollectService;
import com.sky.pojo.Collect;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bluesky
 * @create 2022-11-20-18:25
 */
@RestController
@RequestMapping("/collect")
public class CollectController {


    @Autowired
    private CollectService collectService;

    @PostMapping("/save")
    public R save(@RequestBody Collect collect) {
        return collectService.save(collect);
    }

    @PostMapping("/list")
    public R list(@RequestBody Collect collect){

        return collectService.list(collect.getUserId());
    }

    @PostMapping("/remove")
    public R remove(@RequestBody Collect collect){

        return collectService.remove(collect);
    }

    @PostMapping("/remove/product")
    public R removeByPid(@RequestBody Integer productId){

        return collectService.removeByPid(productId);
    }
}
