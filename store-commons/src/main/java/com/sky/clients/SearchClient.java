package com.sky.clients;

import com.sky.param.ProductSearchParam;
import com.sky.pojo.Product;
import com.sky.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author bluesky
 * @create 2022-11-20-14:36
 */
@FeignClient("search-service")
public interface SearchClient {

    @PostMapping("/search/product")
    R searchProduct(@RequestBody ProductSearchParam productSearchParam);

    @PostMapping("/search/save")
    R saveOrUpdate(@RequestBody Product product);

    @PostMapping("/search/remove")
    R remove(@RequestBody Integer productId);
}
