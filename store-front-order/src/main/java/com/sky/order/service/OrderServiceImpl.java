package com.sky.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.clients.ProductClient;
import com.sky.order.mapper.OrderMapper;
import com.sky.param.OrderParam;
import com.sky.param.PageParam;
import com.sky.param.ProductCollectParam;
import com.sky.pojo.Order;
import com.sky.pojo.Product;
import com.sky.to.OrderToProduct;
import com.sky.utils.R;
import com.sky.vo.AdminOrderVo;
import com.sky.vo.CartVo;
import com.sky.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author bluesky
 * @create 2022-11-22-11:11
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @Override
    public R save(OrderParam orderParam) {

        // 准备数据
        List<Integer> cartIds = new ArrayList<>();
        List<OrderToProduct> orderToProducts = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();

        // 生成数据
        Integer userId = orderParam.getUserId();
        long orderId = System.currentTimeMillis();

        for (CartVo cartVo : orderParam.getProducts()) {
            cartIds.add(cartVo.getId()); // 保存删除的购物车项id
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setNum(cartVo.getNum());
            orderToProduct.setProductId(cartVo.getProductID());
            orderToProducts.add(orderToProduct);

            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderTime(orderId);
            order.setUserId(userId);
            order.setProductId(cartVo.getProductID());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            orderList.add(order); //添加用户信息

        }

        // 订单数据批量保存
        saveBatch(orderList);

        // 发送购物车消息
        rabbitTemplate.convertAndSend("topic.ex", "clear.cart", cartIds);

        // 发送商品服务消息
        rabbitTemplate.convertAndSend("topic.ex", "sub.number", orderToProducts);

        return R.ok("订单保存成功！");
    }

    @Override
    public R list(Integer userId) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Order> list = list(queryWrapper);

        // 分组
        Map<Long, List<Order>> orderMap = list.stream().
                collect(Collectors.groupingBy(Order::getOrderId));

        // 查询商品数据
        List<Integer> productIds = list.stream().map(Order::getProductId).collect(Collectors.toList());

        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> productList = productClient.cartList(productCollectParam);

        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        // 结果封装
        List<List<OrderVo>> result = new ArrayList<>();

        // 遍历订单项集合
        for (List<Order> orders : orderMap.values()) {
            // 封装每一个订单
            List<OrderVo> orderVos = new ArrayList<>();

            for (Order order : orders) {
                OrderVo orderVo = new OrderVo();
                BeanUtils.copyProperties(order, orderVo);
                Product product = productMap.get(order.getProductId());
                orderVo.setProductName(product.getProductPicture());
                orderVo.setProductPicture(product.getProductPicture());
                orderVos.add(orderVo);
            }

            result.add(orderVos);
        }

        R ok = R.ok("订单数据获取成功！", result);
        log.info("OrderServiceImpl.list业务结束，结果:{}", ok);

        return ok;
    }

    @Override
    public R check(Integer productId) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);

        Long count = baseMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("购物车中：" + count + "件商品引用！不能删除！");
        }


        return R.ok("无订单引用，可以删除！");
    }

    @Override
    public R adminList(PageParam pageParam) {

        // 分页参数计算完毕
        int offset = (pageParam.getCurrentPage() - 1) * pageParam.getCurrentPage();
        int pageSize = pageParam.getPageSize();

        List<AdminOrderVo> adminOrderVoList = orderMapper.selectAdminOrder(offset, pageSize);
        log.info("OrderServiceImpl.adminList业务结束，结果:{}", adminOrderVoList);

        return R.ok("订单查询成功！", adminOrderVoList);
    }
}
