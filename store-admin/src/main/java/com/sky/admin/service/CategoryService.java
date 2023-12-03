package com.sky.admin.service;


import com.sky.param.PageParam;
import com.sky.pojo.Category;
import com.sky.utils.R;

/**
 * @author bluesky
 * @create 2022-11-23-14:49
 */
public interface CategoryService {

    /**
     * 分页查询方法
     * @return
     */
    R pageList(PageParam pageParam );

    /**
     * 进行分类数据添加
     * @param category
     * @return
     */
    R save(Category category);

    /**
     * 删除分类
     * @param categoryId
     * @return
     */
    R remove(Integer categoryId);

    /**
     * 修改类别信息
     * @param category
     * @return
     */
    R update(Category category);
}
