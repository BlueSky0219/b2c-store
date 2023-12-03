package com.sky.admin.service.impl;

import com.sky.admin.service.ProductService;
import com.sky.clients.ProductClient;
import com.sky.clients.SearchClient;
import com.sky.param.ProductSaveParam;
import com.sky.param.ProductSearchParam;
import com.sky.pojo.Product;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bluesky
 * @create 2022-11-23-17:36
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private SearchClient searchClient;

    @Override
    public R search(ProductSearchParam productSearchParam) {

        R r = searchClient.searchProduct(productSearchParam);
        log.info("ProductServiceImpl.search业务结束：{}", r);

        return r;
    }

    @Override
    public R save(ProductSaveParam productSaveParam) {
        String userId = request.getSession().getAttribute("userId").toString();
        int id = Integer.parseInt(userId);
        System.out.println("id = " + id);

        R r = productClient.adminCount(productSaveParam,id);
        log.info("ProductServiceImpl.save业务结束：{}", r);

        return r;
    }

    @Override
    public R update(Product product) {

        R r = productClient.adminUpdate(product);
        log.info("ProductServiceImpl.update业务结束：{}", r);

        return r;
    }

    @Override
    public R remove(Integer productId) {

        R r = productClient.adminRemove(productId);
        log.info("ProductServiceImpl.remove业务结束：{}", r);

        return r;
    }

    @Override
    public R list() {

        String userId = request.getSession().getAttribute("userId").toString();
        int id = Integer.parseInt(userId);

        String userRole = request.getSession().getAttribute("userRole").toString();
        int role = Integer.parseInt(userRole);


        R r = productClient.adminList(id,role);

        //log.info("ProductServiceImpl.list业务结束：{}", r);

        return r;
    }

    @Override
    public R adminSave(ProductSaveParam productSaveParam) {

        String userId = request.getSession().getAttribute("userId").toString();
        int id = Integer.parseInt(userId);

        System.out.println("id = " + id);
        System.out.println("productSaveParam = " + productSaveParam);
        System.out.println("productSaveParam = " + productSaveParam.getProductName());

        R r = productClient.adminSave(productSaveParam, id);
        log.info("ProductServiceImpl.save业务结束：{}", r);

        return r;
    }
}
