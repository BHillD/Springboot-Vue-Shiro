package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.model.User;
import com.example.service.EmployeeService;
import com.example.service.LoginService;
import com.example.service.UserService;
import com.example.utlis.CurrentUser;
import com.example.utlis.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity login(@RequestBody JSONObject obj){
        String username = obj.getString("username");
        String password = obj.getString("password");
        if(username != null && password != null){
            try {
                loginService.login(username,password);
                JSONObject user = (JSONObject) JSON.toJSON(CurrentUser.getCurrentUser());
                user.remove("username");
                user.remove("password");
                return Response.ok(user);
            }catch (IncorrectCredentialsException e){
                return Response.unauth("用户名或密码不正确");
            }catch (UnknownAccountException e){
                return Response.unauth("用户名不存在");
            }
        }
        return Response.unauth("输入用户名或密码后重试");
    }


    @PostMapping("/regist")
    public ResponseEntity regist(@RequestBody JSONObject obj){
        String username = obj.getString("username");
        String password = obj.getString("password");
        String name = obj.getString("name");
        if(username == null || password == null || name == null){
            return Response.badReq();
        }
        try {
            userService.registUser(username, password, name);
            return Response.ok();
        } catch (Exception e){
            return Response.err();
        }
    }

    /**
     * 进行注销操作
     * @return
     */
    @GetMapping("/logout")
    public ResponseEntity logout(){
        loginService.logout();
        return Response.ok();
    }
}
