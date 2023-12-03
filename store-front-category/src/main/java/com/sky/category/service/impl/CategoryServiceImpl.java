package com.sky.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.category.mapper.CagtegoryMapper;
import com.sky.category.service.CategoryService;
import com.sky.clients.ProductClient;
import com.sky.param.PageParam;
import com.sky.param.ProductHotParam;
import com.sky.pojo.Category;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-14-17:37
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CagtegoryMapper cagtegoryMapper;

    @Autowired
    private ProductClient productClient;

    @Override
    public R byName(String categoryName) {

        // 封装查询参数
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", categoryName);

        // 查询数据库
        Category category = cagtegoryMapper.selectOne(queryWrapper);

        // 结果封装
        if (category == null) {

            log.info("CategoryServiceImpl.byName业务结束，结果:{}", "类别查询失败!");
            return R.fail("类别查询失败！");
        }

        log.info("CategoryServiceImpl.byName业务结束，结果:{}", "类别查询成功！");
        return R.ok("类别查询成功！", category);
    }

    @Override
    public R hotsCategory(ProductHotParam productHotParam) {

        // 封装查询
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name", productHotParam.getCategoryName()).select("category_id");

        // 查询数据库
        List<Object> ids = cagtegoryMapper.selectObjs(queryWrapper);

        R ok = R.ok("类别集合查询成功", ids);
        log.info("CategoryServiceImpl.hotsCategory业务结束，结果:{}", ok);

        return ok;

    }

    @Override
    public R list() {

        List<Category> categoryList = cagtegoryMapper.selectList(null);

        R ok = R.ok("类别全部数据查询成功！", categoryList);
        log.info("CategoryServiceImpl.list业务结束，结果:{}", ok);

        return ok;
    }

    @Override
    public R listPage(PageParam pageParam) {

        IPage<Category> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());

        page = cagtegoryMapper.selectPage(page, null);

        return R.ok("类别分页数据查询成功！", page.getRecords(), page.getTotal());
    }

    @Override
    public R adminSave(Category category) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", category.getCategoryName());
        Long count = cagtegoryMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("类别已经存在，类别添加失败！");
        }

        int insert = cagtegoryMapper.insert(category);

        log.info("CategoryServiceImpl.adminSave业务结束，结果：{}", insert);

        return R.ok("类别添加成功！");
    }

    @Override
    public R adminRemove(Integer categoryId) {

        Long aLong = productClient.adminCount(categoryId);

        if (aLong > 0) {
            return R.fail("类别数据失败，有" + aLong + "件商品正在引用！");
        }

        int insert = cagtegoryMapper.deleteById(categoryId);
        log.info("CategoryServiceImpl.adminRemove业务结束，结果：{}", insert);

        return R.ok("类别数据删除成功！");
    }

    @Override
    public R adminUpdate(Category category) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", category.getCategoryName());
        Long count = cagtegoryMapper.selectCount(queryWrapper);

        if (count > 0) {

            return R.fail("类别已经存在，修改失败！");
        }

        int insert = cagtegoryMapper.updateById(category);
        log.info("CategoryServiceImpl.adminUpdate业务结束，结果：{}", insert);

        return R.ok("类别修改成功！", insert);
    }

}
