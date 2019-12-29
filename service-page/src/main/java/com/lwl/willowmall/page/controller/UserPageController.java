package com.lwl.willowmall.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author liuweilong
 * date 2019/12/29 1:15 下午
 * desc
 */
@Controller
@RequestMapping("/user")
public class UserPageController {
    @RequestMapping("login")
    public String loginPage(){
        return "login-page";
    }
}
