package com.sky.admin;

import com.sky.clients.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author bluesky
 * @create 2022-11-22-15:01
 */
@MapperScan("com.sky.admin.mapper")
//@EnableCaching
@EnableFeignClients(clients={UserClient.class, CategoryClient.class , SearchClient.class, ProductClient.class, OrderClient.class})
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
