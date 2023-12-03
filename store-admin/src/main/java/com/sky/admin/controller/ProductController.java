package com.sky.admin.controller;

import com.sky.admin.service.ProductService;
import com.sky.admin.utils.AliyunOSSUtils;
import com.sky.param.ProductSaveParam;
import com.sky.param.ProductSearchParam;
import com.sky.pojo.Product;
import com.sky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * @author bluesky
 * @create 2022-11-23-17:31
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

/*    @GetMapping("/list")
    public R adminList(ProductSearchParam productSearchParam) {

        return productService.search(productSearchParam);
    }*/

    @GetMapping("/list")
    public R adminList() {

        return productService.list();
    }

    @PostMapping("/upload")
    public R adminUpload(@RequestParam("img") MultipartFile img) throws Exception {

        String filename = img.getOriginalFilename();
        filename = UUID.randomUUID().toString().replaceAll("-", "") + filename;

        String contentType = img.getContentType();
        byte[] content = img.getBytes();

        int hours = 1000;

        String url = aliyunOSSUtils.uploadImage(filename, content, contentType, hours);
        System.out.println("url = " + url);

        return R.ok("图片上传成功！", url);
    }

/*    @PostMapping("/save")
    public R adminSave(ProductSaveParam productSaveParam){

        return productService.save(productSaveParam);
    }*/

    @PostMapping("/save")
    public R adminSave(ProductSaveParam productSaveParam) {

        return productService.save(productSaveParam);

    }

    @PostMapping("/update")
    public R adminUpdate(Product product) {

        return productService.update(product);
    }

    @PostMapping("/remove")
    public R adminRemove(Integer productId) {

        return productService.remove(productId);
    }

}
