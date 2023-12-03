package com.sky.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.clients.*;
import com.sky.param.ProductHotParam;
import com.sky.param.ProductIdsParam;
import com.sky.param.ProductSaveParam;
import com.sky.param.ProductSearchParam;
import com.sky.pojo.Picture;
import com.sky.pojo.Product;
import com.sky.product.mapper.PictureMapper;
import com.sky.product.mapper.ProductMapper;
import com.sky.product.service.ProductService;
import com.sky.to.OrderToProduct;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author bluesky
 * @create 2022-11-14-18:22
 */
@Service
@Slf4j
public class ProductServiceimpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private CartClient cartClient;

    @Autowired
    private CollectClient collectClient;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PictureMapper pictureMapper;

    // 引入feign客户端需要，在启动类添加配置注解
    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private SearchClient searchClient;

    //@Cacheable(value = "list.product", key = "#categoryName", cacheManager = "cacheManagerDay")
    @Override
    public R promo(String categoryName) {

        R r = categoryClient.byName(categoryName);

        if (r.getCode().equals(R.FAIL_CODE)) {

            log.info("ProductServiceimpl.promo业务结束，结果:{}", "类别查询失败");
            return r;
        }

        // jackson 特点是数据通过 feign 转换成 linkedHashMap
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) r.getData();
        Integer categoryId = (Integer) map.get("category_id");

        // 封装查询参数
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId).orderByDesc("product_sales");

        // 分页！返回的是包装数据，内部有对应的商品集合，也有分页的参数，例如：总条数，总页数等等
        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, queryWrapper);

        List<Product> productList = page.getRecords(); // 指定页的数据
        long total = page.getTotal(); // 获取总条数

        return R.ok("数据查询成功", productList);
    }

    //@Cacheable(value = "list.product", key = "#productHotParam.categoryName")
    @Override
    public R hots(ProductHotParam productHotParam) {

        // 获取ids
        R r = categoryClient.hots(productHotParam);

        if (r.getCode().equals(R.FAIL_CODE)) {

            log.info("ProductServiceimpl.hots业务结束，结果:{}", "类别查询失败");
            return r;
        }

        List<Object> ids = (List<Object>) r.getData();

        // 进行商品数据查询
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", ids).orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, queryWrapper);

        List<Product> records = page.getRecords();

        R ok = R.ok("多类别热门商品查询成功！", records);
        log.info("ProductServiceimpl.hots业务结束，结果:{}", ok);

        return ok;
    }

    @Override
    public R clist() {

        R r = categoryClient.list();
        log.info("ProductServiceimpl.clist业务结束，结果:{}", r);

        return r;
    }

    //@Cacheable(value = "list.product", key = "#productIdsParam.categoryID+'-'+#productIdsParam.currentPage+'-'+#productIdsParam.pageSize")
    @Override
    public R bycategory(ProductIdsParam productIdsParam) {

        List<Integer> categoryID = productIdsParam.getCategoryID();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        if (!categoryID.isEmpty()) {

            queryWrapper.in("category_id", categoryID);
        }

        IPage<Product> page = new Page<>(productIdsParam.getCurrentPage(), productIdsParam.getPageSize());

        page = productMapper.selectPage(page, queryWrapper);

        // 结果集封装
        R ok = R.ok("查询成功", page.getRecords(), page.getTotal());

        log.info("ProductServiceimpl.bycategory业务结束，结果:{}", ok);

        return ok;
    }

    //@Cacheable(value = "product", key = "#productId")
    @Override
    public R detail(Integer productId) {

        Product product = productMapper.selectById(productId);

        R ok = R.ok(product);
        log.info("ProductServiceimpl.detail业务结束，结果:{}", ok);

        return ok;
    }

    //@Cacheable(value = "product", key = "#productId")
    @Override
    public R pictures(Integer productId) {

        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);

        List<Picture> pictureList = pictureMapper.selectList(queryWrapper);

        R ok = R.ok(pictureList);
        log.info("ProductServiceimpl.pictures业务结束，结果:{}", ok);

        return ok;
    }

    // root.methodName 表示当前方法名
    //@Cacheable(value = "list.category", key = "#root.methodName", cacheManager = "cacheManagerDay")
    @Override
    public List<Product> allList() {

        List<Product> productList = productMapper.selectList(null);

        log.info("ProductServiceimpl.allList业务结束，结果:{}", productList);

        return productList;
    }

    @Override
    public R search(ProductSearchParam productSearchParam) {

        R r = searchClient.searchProduct(productSearchParam);
        log.info("ProductServiceimpl.search业务结束，结果:{}", r);

        return r;
    }

    //@Cacheable(value = "list.product", key = "#productIds")
    @Override
    public R ids(List<Integer> productIds) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);

        List<Product> productList = productMapper.selectList(queryWrapper);

        R r = R.ok("类别信息查询成功！", productList);
        log.info("ProductServiceimpl.ids业务结束，结果:{}", r);

        return r;
    }

    @Override
    public List<Product> cartList(List<Integer> productIds) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);

        List<Product> productList = productMapper.selectList(queryWrapper);
        log.info("ProductServiceimpl.cartList业务结束，结果:{}", productList);

        return productList;
    }

    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {

        // 将集合转成map
        Map<Integer, OrderToProduct> map = orderToProducts.stream()
                .collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));

        // 获取商品的id集合
        Set<Integer> productIds = map.keySet();
        // 查询集合对应的商品信息
        List<Product> productList = productMapper.selectBatchIds(productIds);
        // 修改商品信息
        for (Product product : productList) {
            Integer num = map.get(product.getProductId()).getNum();
            product.setProductNum(product.getProductNum() - num); // 减库存
            product.setProductSales(product.getProductSales() + num); // 增加销售额
        }

        // 批量更新
        this.updateBatchById(productList);
        log.info("ProductServiceimpl.subNumber业务结束，结果:{}", "库存和销售量的修改完毕！");

    }

    @Override
    public Long adminCount(Integer categoryId) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);

        Long count = baseMapper.selectCount(queryWrapper);
        log.info("ProductServiceimpl.adminCount业务结束，结果：{}", count);

        return count;
    }

    @CacheEvict(value = "list.product", allEntries = true)
    @Override
    public R adminSave(ProductSaveParam productSaveParam,@RequestParam("adminId")Integer adminId) {

        Product product = new Product();
        System.out.println("adminId = " + adminId);

        BeanUtils.copyProperties(productSaveParam, product);
        product.setAdminId(adminId);

        int rows = productMapper.insert(product);
        log.info("ProductServiceimpl.adminSave业务结束，结果：{}", rows);

        String pictures = productSaveParam.getPictures();

        if (!StringUtils.isEmpty(pictures)) {
            String[] urls = pictures.split("\\+");
            for (String url : urls) {
                Picture picture = new Picture();
                picture.setProductId(product.getProductId());
                picture.setProductPicture(url);
                pictureMapper.insert(picture);
            }
        }
        // 同步搜索服务的数据
        searchClient.saveOrUpdate(product);

        return R.ok("商品数据添加成功！");
    }

    @Override
    public R adminSaveProduct(ProductSaveParam productSaveParam, Integer userId) {

        Product product = new Product();
        product.setAdminId(userId);
        BeanUtils.copyProperties(productSaveParam, product);

        log.info("productSaveParam ==================================：{}" + productSaveParam.getProductName());
        log.info("product ==================================：{}" + product);

        int rows = productMapper.insert(product);
        log.info("ProductServiceimpl.adminSaveProduct业务结束，结果：{}", rows);

        String pictures = productSaveParam.getPictures();

        if (!StringUtils.isEmpty(pictures)) {
            String[] urls = pictures.split("\\+");
            for (String url : urls) {
                Picture picture = new Picture();
                picture.setProductId(product.getProductId());
                picture.setProductPicture(url);
                pictureMapper.insert(picture);
            }
        }
        // 同步搜索服务的数据
        searchClient.saveOrUpdate(product);

        return R.ok("商品数据添加成功！");

    }

    @Override
    public R adminUpdate(Product product) {

        productMapper.updateById(product);

        // 同步搜索服务的数据
        searchClient.saveOrUpdate(product);

        return R.ok("商品数据更新成功！");
    }

    @CacheEvict(value = "product.list", allEntries = true)
    @Override
    public R adminRemove(Integer productId) {

        R r = cartClient.check(productId);
        if ("004".equals(r.getCode())) {

            log.info("ProductServiceimpl.adminRemove业务结束，结果：{}", r.getMsg());
            return r;
        }

        r = orderClient.check(productId);
        if ("004".equals(r.getCode())) {

            log.info("ProductServiceimpl.adminRemove业务结束，结果：{}", r.getMsg());
            return r;
        }

        // 删除商品
        productMapper.deleteById(productId);

        // 删除商品图片
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        pictureMapper.delete(queryWrapper);

        // 删除收藏中和本商品有关的！
        collectClient.remove(productId);

        // 同步数据
        searchClient.remove(productId);

        return R.ok("商品删除成功！");
    }

    @Override
    public R adminList(Integer userId, Integer userRole) {

        System.out.println("userRole = " + userRole);

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        if (userRole == 0) {
            List<Product> productList = productMapper.selectList(null);
            log.info("ProductServiceimpl.adminList业务结束，结果:{}", productList);
            return R.ok("商品查询成功！", productList);
        }

        queryWrapper.eq("admin_id", userId);

        List<Product> productList = productMapper.selectList(queryWrapper);
        log.info("ProductServiceimpl.adminList业务结束，结果:{}", productList);

        return R.ok("商品查询成功！", productList);
    }


}
