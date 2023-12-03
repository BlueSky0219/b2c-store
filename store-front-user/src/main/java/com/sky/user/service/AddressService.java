package com.sky.user.service;

import com.sky.pojo.Address;
import com.sky.utils.R;

/**
 * @author bluesky
 * @create 2022-11-14-13:37
 */
public interface AddressService {

    /**
     * 根据用户id查询地址数据
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 插入地址数据，插入成功以后，要返回新的数据集合！
     * @param address
     * @return
     */
    R save(Address address);

    /**
     * 根据id 删除地址数据
     * @param id
     * @return
     */
    R remove(Integer id);
}
