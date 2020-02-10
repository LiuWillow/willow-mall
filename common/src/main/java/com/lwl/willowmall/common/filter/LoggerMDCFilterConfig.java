package com.lwl.willowmall.common.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志里面加 MDC 追踪
 */
@Configuration
public class LoggerMDCFilterConfig {
    @Bean
    public FilterRegistrationBean retailLoggerMDCFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new LoggerMDCFilter());  //要注册的过滤器
        registrationBean.addUrlPatterns("/*");              //过滤器要处理的URL
        return registrationBean;
    }
}
