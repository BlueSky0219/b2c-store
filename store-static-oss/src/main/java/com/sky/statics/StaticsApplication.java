package com.sky.statics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author bluesky
 * @create 2022-11-14-15:16
 */
@EnableDiscoveryClient
@SpringBootApplication
public class StaticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticsApplication.class, args);
    }
}
