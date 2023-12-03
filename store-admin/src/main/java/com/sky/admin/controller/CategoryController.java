package com.sky.admin.controller;

import com.sky.admin.service.CategoryService;
import com.sky.param.PageParam;
import com.sky.pojo.Category;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bluesky
 * @create 2022-11-23-14:41
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    private R pageList(PageParam pageParam) {

        return categoryService.pageList(pageParam);
    }

    @PostMapping("/save")
    private R save(Category category) {

        return categoryService.save(category);
    }

    @PostMapping("/remove")
    private R remove(Integer categoryId) {

        return categoryService.remove(categoryId);
    }

    @PostMapping("/update")
    private R update(Category category) {

        return categoryService.update(category);
    }
}
