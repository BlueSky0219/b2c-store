package com.sky.category.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sky.category.service.CategoryService;
import com.sky.param.ProductHotParam;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author bluesky
 * @create 2022-11-14-17:00
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R byName(@PathVariable String categoryName) {

        if (StringUtils.isEmpty(categoryName)) {
            return R.fail("类别名称为null，无法查询类别数据！");
        }

        return categoryService.byName(categoryName);

    }

    /**
     * 热门类别id查询
     * @param productHotParam
     * @param result
     * @return
     */
    @PostMapping("/hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("类别及和查询失败！");
        }

        return categoryService.hotsCategory(productHotParam);

    }

    @GetMapping("/list")
    public R list(){


        return categoryService.list();
    }
}
