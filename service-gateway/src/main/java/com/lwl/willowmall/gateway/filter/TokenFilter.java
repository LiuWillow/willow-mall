package com.lwl.willowmall.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.lwl.willowmall.common.em.ResponseCodeEnum;
import com.lwl.willowmall.common.vo.Response;
import com.lwl.willowmall.gateway.constant.FilterConst;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author liuweilong
 * date 2019/12/29 5:36 下午
 * desc
 */
@Order(FilterConst.TOKEN_PRECEDENCE)
@Slf4j
@Component
public class TokenFilter implements GlobalFilter {
    private static Integer[] i = new Integer[]{1, 3, 5};
    private static final String PAGE_PREFIX = "/page";
    /**
     * 不需要拦截的URL列表
     */
    private static final Set<String> NOT_INTERCEPT = new HashSet<>();
    static {
        NOT_INTERCEPT.add("/api/mall/user/login");
    }

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String urlPath = request.getPath().toString();
        if (NOT_INTERCEPT.contains(urlPath)){
            //如果不需要拦截就直接返回
            return chain.filter(exchange);
        }
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        List<HttpCookie> tokenCookies = cookies.get(FilterConst.TOKEN_COOKIE_KEY);
        if (CollectionUtils.isEmpty(tokenCookies)) {
            log.warn("访问{}被拦截，没有有效token", urlPath);
            return failed(exchange);
        }
        String tokenString = tokenCookies.get(0).getValue();
        if (StringUtils.isEmpty(tokenString)) {
            log.warn("tokenString为空");
            return failed(exchange);
        }
        log.info("携带的cookie信息：{}", JSON.toJSONString(cookies));
        //TODO 先解密延签，看看时间戳是否有问题，再比较nonce，没问题则下一步
        //TODO 从session中获取到用户对应的token，解密，然后拿到对应的数据，写到header当中，方便下游服务获取用户信息

        //TODO 登录成功，则更新session超时时间

        //TODO 验证成功，每次请求怎么延长时间

        return chain.filter(exchange);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(1, 1);
    }

    /**
     * 将String转化为DataBuffer
     * @param value
     * @return
     */
    private DataBuffer string2Buffer(String value){
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    private Mono<Void> failed(ServerWebExchange exchange){
        DataBuffer dataBuffer = string2Buffer(JSON.toJSONString(Response.error(ResponseCodeEnum.FAILED)));
        return exchange.getResponse().writeWith(Flux.just(dataBuffer));
    }
}
