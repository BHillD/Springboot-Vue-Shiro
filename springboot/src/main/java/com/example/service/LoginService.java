package com.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.utlis.CurrentUser;
import com.example.utlis.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;


/**
 * @author
 * @date 2019/5/31
 * @description 用户登录相关操作服务
 */

@Service
public class LoginService{

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户信息，登录失败返回错误信息
     */

    public void login(String username, String password) throws UnknownAccountException,IncorrectCredentialsException{
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);
    }

    /**
     * 进行注销操作
     * @return
     */
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
