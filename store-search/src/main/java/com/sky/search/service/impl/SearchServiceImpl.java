package com.sky.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.doc.ProductDoc;
import com.sky.param.ProductSearchParam;
import com.sky.pojo.Product;
import com.sky.search.service.SearchService;
import com.sky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bluesky
 * @create 2022-11-19-16:23
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public R search(ProductSearchParam productSearchParam) {

        // 获取到表
        SearchRequest searchRequest = new SearchRequest("product");
        // 获取前端传来的查询字段
        String search = productSearchParam.getSearch();

        if (StringUtils.isEmpty(search)) {

            // null 不添加all关键字，查询全部即可
            searchRequest.source().query(QueryBuilders.matchAllQuery()); // 查询全部数据

        } else {
            // 不为null
            // 添加all的匹配
            searchRequest.source().query(QueryBuilders.matchQuery("all", search));
        }

        // 进行分页数据添加
        searchRequest.source().from((productSearchParam.getCurrentPage() - 1) * productSearchParam.getPageSize()); // 偏移量 （当前页数-1）* 页容量
        searchRequest.source().size(productSearchParam.getPageSize());

        SearchResponse searchResponse;

        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("查询错误");
        }

        SearchHits hits = searchResponse.getHits();

        // 查询符合的数量
        long total = hits.getTotalHits().value;

        // 数据集合
        SearchHit[] hitsHits = hits.getHits();

        List<Product> productList = new ArrayList<>();

        // json 处理器
        ObjectMapper objectMapper = new ObjectMapper();

        for (SearchHit hitsHit : hitsHits) {
            // 查询的内容数量！productDoc模型对应的json数据
            String sourceAsString = hitsHit.getSourceAsString();

            Product product = null;

            try {
                // 封装成product
                // productDic all -product 如果没有all的数学会报错！jackson提供没有属性的注解
                // TODO:修改product实体类，添加忽略没有属性的注解！
                product = objectMapper.readValue(sourceAsString, Product.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            productList.add(product);
        }

        // 封装数据
        R r = R.ok(null, productList, total);
        log.info("SearchServiceImpl.search业务结束，结果:{}", r);

        return r;

    }

    @Override
    public R save(Product product) throws IOException {

        IndexRequest indexRequest = new IndexRequest("product").id(product.getProductId().toString());
        ProductDoc productDoc = new ProductDoc(product);

        // 转JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(productDoc);

        indexRequest.source(json, XContentType.JSON);

        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        return R.ok("数据同步成功！");
    }

    @Override
    public R remove(Integer productId) throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest("product").id(productId.toString());

        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

        return R.ok("es库的数据删除成功！");
    }
}
