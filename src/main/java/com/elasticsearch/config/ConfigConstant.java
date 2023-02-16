package com.elasticsearch.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("elasticsearch")
@Getter
@Setter
public class ConfigConstant {
    @Value("elasticsearch.host")
    private String host;

    @Value("elasticsearch.port")
    private String port;

}
