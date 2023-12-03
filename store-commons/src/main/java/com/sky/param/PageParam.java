package com.sky.param;

import lombok.Data;

/**
 * @author bluesky
 * @create 2022-11-19-16:15
 */
@Data
public class PageParam {
    private Integer currentPage = 1;

    private Integer pageSize = 15;
}
