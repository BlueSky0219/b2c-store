package com.sky.clients;

import com.sky.param.PageParam;
import com.sky.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author bluesky
 * @create 2022-11-24-16:29
 */
@FeignClient("order-service")
public interface OrderClient {

    @PostMapping("/order/remove/check")
    R check(@RequestBody Integer productId);

    @PostMapping("/order/remove/list")
    R list(@RequestBody PageParam pageParam);
}
