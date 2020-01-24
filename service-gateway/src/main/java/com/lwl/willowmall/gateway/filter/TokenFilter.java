package com.lwl.willowmall.gateway.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

/**
 * author liuweilong
 * date 2019/12/29 5:36 下午
 * desc
 */
@Order(-1)
@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    private static final String PAGE_PREFIX = "/page";
    /**
     * 不需要拦截的URL列表
     */
    private static final Set<String> NOT_INTERCEPT = new HashSet<>();
    static {
        NOT_INTERCEPT.add("/api/user/login");
    }

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (NOT_INTERCEPT.contains(request.getPath().toString())){
            //如果不需要拦截就直接返回
            return chain.filter(exchange);
        }
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        log.info("携带的cookie信息：{}", JSON.toJSONString(cookies));
        //TODO 过滤下cookie

        return chain.filter(exchange);
    }

    public int getOrder() {
        return 0;
    }

    private class SS{
        private int ll;

        public void a(){
        }
    }
}
