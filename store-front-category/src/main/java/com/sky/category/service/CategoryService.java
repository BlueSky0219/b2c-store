package com.sky.category.service;

import com.sky.param.PageParam;
import com.sky.param.ProductHotParam;
import com.sky.pojo.Category;
import com.sky.utils.R;

/**
 * @author bluesky
 * @create 2022-11-14-17:36
 */
public interface CategoryService {

    /**
     * 根据类别名称查询类别对象
     *
     * @param categoryName
     * @return
     */
    R byName(String categoryName);

    /**
     * 根据传入的热门类别名称集合返回类别对应的id集合
     * @param productHotParam
     * @return
     */
    R hotsCategory(ProductHotParam productHotParam);

    /**
     * 查询类别数据，进行返回
     * @return
     */
    R list();

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);

    /**
     * 添加类别信息
     * @param category
     * @return
     */
    R adminSave(Category category);

    /**
     * 删除数据
     * @param categoryId
     * @return
     */
    R adminRemove(Integer categoryId);

    /**
     * 类别修改功能
     * @param category
     * @return
     */
    R adminUpdate(Category category);
}
