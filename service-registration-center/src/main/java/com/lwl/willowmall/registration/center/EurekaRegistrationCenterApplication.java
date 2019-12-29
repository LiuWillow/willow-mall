package com.lwl.willowmall.registration.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * author liuweilong
 * date 2019/12/29 12:37 下午
 * desc
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaRegistrationCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRegistrationCenterApplication.class, args);
    }
}
