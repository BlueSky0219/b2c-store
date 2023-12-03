package com.sky.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.param.ProductHotParam;
import com.sky.param.ProductIdsParam;
import com.sky.param.ProductSaveParam;
import com.sky.param.ProductSearchParam;
import com.sky.pojo.Admin;
import com.sky.pojo.Product;
import com.sky.to.OrderToProduct;
import com.sky.utils.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-14-18:22
 */
public interface ProductService extends IService<Product> {

    /**
     * 单类别查询 查询热门商品 至多7条数据
     *
     * @param categoryName
     * @return
     */
    R promo(String categoryName);

    /**
     * 多类别热门商品查询 根据类别名称集合 至多查询7条
     *
     * @param productHotParam
     * @return
     */
    R hots(ProductHotParam productHotParam);

    /**
     * 查询类别商品集合
     *
     * @return
     */
    R clist();

    /**
     * 传入了id则根据id查询并分页，没传入则查询全部
     *
     * @param productIdsParam
     * @return
     */
    R bycategory(ProductIdsParam productIdsParam);

    /**
     * 根据商品ID查询商品详情信息
     *
     * @param productId
     * @return
     */
    R detail(Integer productId);

    /**
     * 查询商品对应的详情集合
     * @param productId
     * @return
     */
    R pictures(Integer productId);

    /**
     * 搜索服务调用，获取全部商品数据
     * @return
     */
    List<Product> allList();

    /**
     * 搜索业务，需要调用搜索服务
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 根据商品id集合查询商品id
     * @param productIds
     * @return
     */
    R ids(List<Integer> productIds);

    /**
     * 根据商品id，查询商品id集合
     * @param productIds
     * @return
     */
    List<Product> cartList(List<Integer> productIds);

    /**
     * 修改库存和增加销售量
     * @param orderToProducts
     */
    void subNumber(List<OrderToProduct> orderToProducts);

    /**
     * 类别对应的商品数量查询
     * @param categoryId
     * @return
     */
    Long adminCount(Integer categoryId);

    /**
     * 商品保存业务
     * @param productSaveParam
     * @return
     */
    R adminSave(ProductSaveParam productSaveParam,@RequestParam("adminId")Integer adminId);

    /**
     *  商品数据更新
     * @param product
     * @return
     */
    R adminUpdate(Product product);

    /**
     * 商品删除
     * @param productId
     * @return
     */
    R adminRemove(Integer productId);


    /**
     * 后台展示商家对应的商品
     * @param userId
     * @return
     */
    R adminList(Integer userId,Integer userRole);

    /**
     * 给商家添加对应的商品
     * @param userId
     * @return
     */
    R adminSaveProduct(ProductSaveParam productSaveParam, Integer userId);
}
