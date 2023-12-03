package com.sky.clients;

import com.sky.param.CartListParam;
import com.sky.param.PageParam;
import com.sky.pojo.User;
import com.sky.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author bluesky
 * @create 2022-11-22-17:12
 */
@FeignClient("user-service")
public interface UserClient {

    @PostMapping("/user/admin/list")
     R listPage(@RequestBody PageParam pageParam);

    @PostMapping("/user/admin/remove")
    R remove(@RequestBody CartListParam cartListParam);

    @PostMapping("/user/admin/update")
    R update(@RequestBody User user);

    @PostMapping("/user/admin/save")
    R save(@RequestBody User user);
}
