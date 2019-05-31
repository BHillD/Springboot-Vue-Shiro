package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.service.LoginService;
import com.example.utlis.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @date 2019/5/31
 * @description
 */


@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    LoginService loginService;


    /**
     * 验证用户名与密码 进行登录操作
     * @param obj 存有用户账号与密码的JSON对象
     * @return 登录成功返回用户信息
     *         登录失败返回错误信息
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject obj){
        String username = obj.getString("username");
        String password = obj.getString("password");
        if(username != null && password != null){
            return loginService.login(username, password);
        }
        return Response.err("请输入账号密码后重试");
    }


    /**
     * 进行注销操作
     * @return
     */
    @GetMapping("/logout")
    public JSONObject logout(){

        return loginService.logout();
    }
}
