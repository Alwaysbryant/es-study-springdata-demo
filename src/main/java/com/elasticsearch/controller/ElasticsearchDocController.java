package com.elasticsearch.controller;

import com.elasticsearch.dao.ProductDao;
import com.elasticsearch.pojo.Product;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/es/doc")
public class ElasticsearchDocController {
    @Autowired
    private ElasticsearchRestTemplate restTemplate;
    @Autowired
    private ProductDao dao;

    @PostMapping
    public String create() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("手机");
        product.setCategory("电子产品");
        product.setPrice(3999.00);
        product.setImg("https://ssss");
        Product result = dao.save(product);
        return result == null ? "创建文档失败" : "创建文档成功";
    }

    @PutMapping
    public String update() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("手机");
        product.setCategory("电子产品");
        product.setPrice(2999.00);
        product.setImg("https://www.111.sss");
        Product result = dao.save(product);
        return result == null ? "更新文档失败" : "更新文档成功";
    }

    @DeleteMapping
    public String delete() {
        dao.deleteById(1L);
        return "删除成功";
    }

    @GetMapping
    public String get() {
        StringBuilder builder = new StringBuilder();
        builder.append("根据id查询: ");
        // 根据id查询
        Optional<Product> optional = dao.findById(1L);
        if (optional.isPresent()) {
            Product product = optional.get();
            builder.append(product.toString());
        }
        builder.append("========查询全部");
        // 查询全部
        Iterable<Product> all = dao.findAll();
        all.forEach(item -> {
            System.out.println(item.toString());
            builder.append(item.toString()).append(";");
        });
        return new String(builder);
    }

    @PostMapping("/save_all")
    public String saveAll() {
        List<Product> list = new ArrayList();
        for (long i = 1l; i < 5; i++) {
            Product product = new Product();
            product.setId(i);
            product.setTitle("手机" + i);
            product.setCategory("电子产品");
            product.setPrice(3999.00);
            product.setImg("https://ssss");
            list.add(product);
        }
        Iterable<Product> products = dao.saveAll(list);
        return "成功";
    }

    @GetMapping("/page")
    public String page(Integer current, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest request = PageRequest.of(current, pageSize, sort);
        Page<Product> all = dao.findAll(request);
        StringBuilder builder = new StringBuilder();
        builder.append("分页查询: ");
        all.forEach(item -> {
            System.out.println(item.toString());
            builder.append(item.toString()).append(";");
        });
        return new String(builder);
    }

    @GetMapping("/query")
    public String query(String value) {
        TermQueryBuilder builder = QueryBuilders.termQuery("title", value);
        Iterable<Product> search = dao.search(builder);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("条件查询: ");
        search.forEach(item -> {
            System.out.println(item.toString());
            stringBuilder.append(item.toString()).append(";");
        });
        return new String(stringBuilder);
    }



}
