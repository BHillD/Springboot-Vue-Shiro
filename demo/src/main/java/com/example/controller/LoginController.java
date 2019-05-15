package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject obj){
        String username = obj.getString("username");
        String password = obj.getString("password");
        return loginService.login(username, password);
    }

    @RequestMapping("/logout")
    public JSONObject logout(){
        return loginService.logout();
    }
}
