package com.sky.doc;

import com.sky.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bluesky
 * @create 2022-11-19-15:00
 */
@Data
@NoArgsConstructor
public class ProductDoc extends Product {

    /**
     * 商品名称和商品标题和商品描述的综合值
     */
    private String all;

    public ProductDoc(Product product){
        super(product.getProductId(), product.getProductName(), product.getCategoryId(),
                product.getProductTitle(), product.getProductIntro(), product.getProductPicture(),
                product.getProductPrice(), product.getProductSellingPrice(), product.getProductNum(),
                product.getProductSales(),product.getProductSales());

        this.all = product.getProductName()+product.getProductTitle()+product.getProductIntro();
    }
}
