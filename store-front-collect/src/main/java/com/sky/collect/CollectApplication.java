package com.sky.collect;

import com.sky.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author bluesky
 * @create 2022-11-21-12:11
 */
@MapperScan("com.sky.collect.mapper")
@EnableDiscoveryClient
@EnableFeignClients(clients = {ProductClient.class})
@SpringBootApplication
public class CollectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class, args);
    }
}
