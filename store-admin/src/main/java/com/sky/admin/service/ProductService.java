package com.sky.admin.service;

import com.sky.param.ProductSaveParam;
import com.sky.param.ProductSearchParam;
import com.sky.pojo.Product;
import com.sky.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author bluesky
 * @create 2022-11-23-17:34
 */
public interface ProductService {

    /**
     * 全部商品查询和搜索查询的方法
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 进行商品数据保存
     * @param productSaveParam
     * @return
     */
    R save(ProductSaveParam productSaveParam);

    /**
     * 商品更新
     * @param product
     * @return
     */
    R update(Product product);

    /**
     * 商品移出
     * @param productId
     * @return
     */
    R remove(Integer productId);



    /**
     * 查询商家对应的商品
     * @param
     * @return
     */
    R list();

    /**
     * 添加商家对应的商品
     * @param productSaveParam
     * @return
     */
    R adminSave(ProductSaveParam productSaveParam);
}
