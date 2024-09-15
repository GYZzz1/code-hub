package com.gyzjc.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : UserController
 * @Description : satoken测试
 * @Author : GYZzz
 * @Date: 2024-09-15 11:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/doLogin")
    public SaResult doLogin(String userName, String password) {
        if ("zhang".equals(userName) && "123456".equals(password)) {
            StpUtil.login(10001);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return SaResult.data(tokenInfo);
        }
        return SaResult.error("登录失败");
    }

    @GetMapping("/isLogin")
    public String isLogin() {
        return "是否登录" + StpUtil.isLogin();
    }
}
