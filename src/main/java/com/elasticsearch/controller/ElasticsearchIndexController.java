package com.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/es/index")
public class ElasticsearchIndexController {
    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @PutMapping
    public String create() {
        return "创建索引成功";
    }

    @DeleteMapping
    public String delete() {
        boolean shopping = restTemplate.deleteIndex("shopping");
        return shopping ? "删除成功" : "删除失败";
    }
}
