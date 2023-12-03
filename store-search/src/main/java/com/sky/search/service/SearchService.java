package com.sky.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sky.param.ProductSearchParam;
import com.sky.pojo.Product;
import com.sky.utils.R;

import java.io.IOException;

/**
 * @author bluesky
 * @create 2022-11-19-16:22
 */
public interface SearchService {

    /**
     * 根据关键字和分页进行数据库查询
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 商品同步：插入和更新
     * @param product
     * @return
     */
    R save(Product product) throws IOException;

    /**
     * 进行es库的商品删除
     * @param productId
     * @return
     */
    R remove(Integer productId) throws IOException;
}
