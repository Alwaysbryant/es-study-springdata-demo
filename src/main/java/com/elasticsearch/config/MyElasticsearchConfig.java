package com.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class MyElasticsearchConfig /** extends AbstractElasticsearchConfiguration */
{
    @Resource
    private ConfigConstant configConstant;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(configConstant.getHost(), Integer.parseInt(configConstant.getPort()), "http")));
    }
}
