package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.service.EmployeeService;
import com.example.service.LoginService;
import com.example.service.UserService;
import com.example.utlis.Response;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @date 2019/5/31
 * @description
 */


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;


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
        return Response.err("账号或密码错误");
    }


    @PostMapping("/regist")
    public JSONObject regist(@RequestBody JSONObject obj){
        String username = obj.getString("username");
        String password = obj.getString("password");
        String name = obj.getString("name");
        Md5Hash md5Hash = new Md5Hash(password,username,1);
        password = md5Hash.toString();
        userService.registUser(username, password, name);
        return Response.ok();
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
