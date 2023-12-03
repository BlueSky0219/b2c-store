package com.sky.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sky.pojo.Order;
import com.sky.vo.AdminOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-22-11:14
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 查询后台管理的数据方法
     *
     * @param offset
     * @param pageSize
     * @return
     */
    List<AdminOrderVo> selectAdminOrder(@Param("offset") int offset,@Param("pageSize") int pageSize);
}
