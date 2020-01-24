package com.lwl.willowmall.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * author liuweilong
 * date 2020/1/1 9:00 下午
 * desc
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class SwaggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }
}
