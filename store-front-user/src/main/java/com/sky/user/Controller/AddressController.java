package com.sky.user.Controller;

import com.sky.param.AddressListParam;
import com.sky.param.AddressParam;
import com.sky.param.AddressRemoveParam;
import com.sky.pojo.Address;
import com.sky.user.service.AddressService;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bluesky
 * @create 2022-11-14-13:31
 */
@RestController
@RequestMapping("user/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/list")
    public R list(@RequestBody @Validated AddressListParam addressListParam, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("参数异常，查询失败！");
        }

        return addressService.list(addressListParam.getUserId());
    }

    @PostMapping("/save")
    public R save(@RequestBody @Validated AddressParam addressParam, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("参数异常，保存失败！");
        }

        Address address = addressParam.getAdd();
        address.setUserId(addressParam.getUserId());

        return addressService.save(address);

    }

    @PostMapping("/remove")
    public R remove(@RequestBody @Validated AddressRemoveParam addressRemoveParam ,BindingResult result) {
        if (result.hasErrors()) {

            return R.fail("参数异常，删除失败");
        }

        return addressService.remove(addressRemoveParam.getId());
    }


}
