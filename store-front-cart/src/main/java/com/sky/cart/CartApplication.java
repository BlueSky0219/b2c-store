package com.sky.cart;

import com.sky.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author bluesky
 * @create 2022-11-21-15:14
 */
@MapperScan("com.sky.cart.mapper")
@EnableDiscoveryClient
@EnableFeignClients(clients = ProductClient.class)
@SpringBootApplication
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}

