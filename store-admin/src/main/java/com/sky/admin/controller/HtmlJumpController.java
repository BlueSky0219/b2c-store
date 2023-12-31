package com.sky.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.clients.CategoryClient;
import com.sky.pojo.Category;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class HtmlJumpController {

    @Autowired
    private CategoryClient categoryClient;

    /**
     * 设计欢迎页面跳转controller
     *
     * @return login 登录页面
     */
    @GetMapping({"/", "index.html", "index"})
    public String welcome() {
        log.info("HtmlJumpController.welcome 跳转登录页面!");
        return "login";
    }


    /**
     * 登录成功跳转到index页面!
     *
     * @return
     */
    @GetMapping("/home")
    public String home() {
        log.info("HtmlJumpController.home登录成功,跳转程序首页!index页面!");
        return "index";
    }

    /**
     * 跳转用户管理页面
     */
    @GetMapping("/user")
    public String user() {
        log.info("HtmlJumpController.user,跳转用户管理!user页面!");
        return "user/user";
    }

    /**
     * 跳转商品管理页面
     */
    @GetMapping("/product")
    public String product() {
        log.info("HtmlJumpController.product,跳转商品管理!product页面!");
        return "product/product";
    }


    /**
     * 跳转类别管理页面
     */
    @GetMapping("/category")
    public String category() {
        log.info("HtmlJumpController.category,跳转用户管理!category页面!");
        return "category/category";
    }


    /**
     * 跳转订单管理页面
     */
    @GetMapping("/order")
    public String order() {
        log.info("HtmlJumpController.order,跳转用户管理!order页面!");
        return "order/order";
    }

    /**
     * 打开编辑用户页面
     */
    @GetMapping("/user/update/html")
    public String userUpdateHtml() {
        log.info("HtmlJumpController.userUpdateHtml业务结束，结果:{}");
        return "user/edit";
    }


    /**
     * 打开编辑用户页面
     */
    @GetMapping("/user/save/html")
    public String userSaveHtml() {
        log.info("HtmlJumpController.userSaveHtml业务结束，结果:{}");
        return "user/add";
    }


    /**
     * 打开编辑类别页面
     */
    @GetMapping("/category/update/html")
    public String categoryUpdateHtml() {
        log.info("HtmlJumpController.categoryUpdateHtml业务结束，结果:{}");
        return "category/edit";
    }

    @GetMapping("/category/save/html")
    public String categorySaveHtml() {
        log.info("HtmlJumpController.categorySaveHtml结束，结果:{}");
        return "category/add";
    }


    /**
     * 商品保存页面跳转
     *
     * @return
     */
    @GetMapping("/product/save/html")
    public String productSaveHtml(Model model) throws JsonProcessingException {
        log.info("HtmlJumpController.productSaveHtml业务结束，结果:{}");

        //查询类别列表,存入共享域
        R r = categoryClient.list();
        List<LinkedHashMap> data = (List<LinkedHashMap>) r.getData();

        List<Category> categoryList = new ArrayList<>();

        for (LinkedHashMap map : data) {
            Category category = new Category();
            category.setCategoryId((Integer) map.get("category_id"));
            category.setCategoryName((String) map.get("category_name"));
            categoryList.add(category);
        }

        model.addAttribute("clist", categoryList);
        return "product/add";
    }

    /**
     * 商品保存页面跳转
     *
     * @return
     */
    @GetMapping("/product/update/html")
    public String productUpdateHtml(Model model) {
        log.info("HtmlJumpController.productUpdateHtml业务结束，结果:{}");

        //查询类别列表,存入共享域
        R r = categoryClient.list();
        List<LinkedHashMap> data = (List<LinkedHashMap>) r.getData();

        List<Category> categoryList = new ArrayList<>();

        for (LinkedHashMap map : data) {
            Category category = new Category();
            category.setCategoryId((Integer) map.get("category_id"));
            category.setCategoryName((String) map.get("category_name"));
            categoryList.add(category);
        }

        model.addAttribute("clist", categoryList);
        return "product/edit";
    }

    @GetMapping("/fenxi")
    public String fenxi() {
        log.info("HtmlJumpController.fenxi,跳转数据分析!fenxi页面!");
        return "fenxi/fenxi";
    }

    @GetMapping("/fenxi1")
    public String fenxi1() {
        log.info("HtmlJumpController.fenxi,跳转数据分析!fenxi页面!");
        return "fenxi1/fenxi1";
    }

    @GetMapping("/fenxi2")
    public String fenxi2() {
        log.info("HtmlJumpController.fenxi,跳转数据分析!fenxi页面!");
        return "fenxi2/fenxi2";
    }

    @GetMapping("/fenxi3")
    public String fenxi3() {
        log.info("HtmlJumpController.fenxi,跳转数据分析!fenxi页面!");
        return "fenxi3/fenxi3";
    }

    @GetMapping("/fenxi4")
    public String fenxi4() {
        log.info("HtmlJumpController.fenxi,跳转数据分析!fenxi页面!");
        return "fenxi4/fenxi4";
    }

    @GetMapping("/fenxi5")
    public String fenxi5() {
        log.info("HtmlJumpController.fenxi,跳转数据分析!fenxi页面!");
        return "fenxi5/fenxi5";
    }
}

