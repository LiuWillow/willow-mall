package com.lwl.api.mall.controller;

import com.lwl.willowmall.common.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * author liuweilong
 * date 2020/1/23 10:38 下午
 * desc
 */
@RestController
@RequestMapping("/test")
@Api("test")
public class TestController {
    @GetMapping("hi")
    @ApiOperation(value = "hi")
    public Response<?> hi(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        return Response.success();
    }
}
