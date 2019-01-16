package com.example.controller;

import com.example.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController

@RequestMapping("/api")
public class HelloController {
    private int pointNum = 0;

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    private Map<String, User> loginMap = new HashMap<>();

    @RequestMapping("/login")
    public String login(@RequestBody User user) {
        if (user.getAccount() != null && user.getPassword() != null) {
            User u = loginMap.get(user.getAccount());
            if (u == null) {
                return "用户不存在";
            }
            if (u.getPassword().equals(user.getPassword())) {
                u.setPointNum(u.getPointNum() + 5);
                loginMap.put(u.getAccount(), u);
                return "登录成功，当前积分：" + u.getPointNum();
            } else {
                return "密码错误";
            }
        }
        return "请输入账号、密码";
    }


    @RequestMapping("/register")
    public String reg(@RequestBody User user) {
        if (user.getAccount() != null && user.getPassword() != null) {
            User u = new User();
            u.setAccount(user.getAccount());
            u.setPassword(user.getPassword());
            loginMap.put(user.getAccount(), u);
            return "register success:" + user.getAccount();
        }
        return "请输入账号、密码";
    }

}

