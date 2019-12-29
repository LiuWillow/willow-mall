package com.lwl.willowmall.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * author liuweilong
 * date 2019/12/29 4:26 下午
 * desc
 */
@RestController
public class UserController {
    @GetMapping("{id}")
    public String getById(@PathVariable("id") Long userId){
        return userId.toString();
    }
}
