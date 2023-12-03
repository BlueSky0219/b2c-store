package com.sky.admin.service.impl;

import com.sky.admin.service.CategoryService;
import com.sky.clients.CategoryClient;
import com.sky.param.PageParam;
import com.sky.pojo.Category;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author bluesky
 * @create 2022-11-23-14:49
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryClient categoryClient;

    @Cacheable(value = "list.category",key = "#pageParam.currentPage+"+'-'+"#pageParam.pageSize")
    @Override
    public R pageList(PageParam pageParam) {

        R r = categoryClient.adminPageList(pageParam);
        log.info("CategoryServiceImpl.pageList业务结束，结果：{}", r);

        return r;
    }

    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R save(Category category) {

        R r = categoryClient.adminSave(category);
        log.info("CategoryServiceImpl.save业务结束，结果：{}", r);

        return r;
    }

    @Override
    public R remove(Integer categoryId) {
        R r = categoryClient.adminRemove(categoryId);
        log.info("CategoryServiceImpl.remove业务结束，结果：{}", r);

        return r;
    }

    @CacheEvict(value = "list.category",allEntries = true)
    @Override
    public R update(Category category) {

        R r = categoryClient.adminUpdate(category);
        log.info("CategoryServiceImpl.update业务结束，结果：{}", r);

        return r;
    }
}
