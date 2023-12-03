package com.sky.product.listener;

import com.sky.product.service.ProductService;
import com.sky.to.OrderToProduct;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-22-11:51
 */
@Component
public class ProductRabbitMqListener {

    @Autowired
    private ProductService productService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sub.queue"),
                    exchange=@Exchange("topic.ex"),
                    key="sub.number"
            )
    )
    public void subNumber(List<OrderToProduct> orderToProducts){


        productService.subNumber(orderToProducts);
    }
}
