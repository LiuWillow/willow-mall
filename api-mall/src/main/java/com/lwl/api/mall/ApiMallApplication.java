package com.lwl.api.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * author liuweilong
 * date 2020/1/23 10:33 下午
 * desc
 */
@SpringBootApplication
@EnableEurekaClient
public class ApiMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiMallApplication.class, args);
    }
}
