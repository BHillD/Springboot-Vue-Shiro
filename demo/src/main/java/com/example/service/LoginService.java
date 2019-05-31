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

    public JSONObject login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            JSONObject obj = (JSONObject) JSON.toJSON(CurrentUser.getCurrentUser());
            obj.remove("username");
            obj.remove("password");
            return Response.ok(obj);
        } catch (UnknownAccountException e){
            return Response.err("账号不存在");
        } catch (IncorrectCredentialsException e){
            return Response.err("账号或密码错误");
        }
    }


    public JSONObject logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return Response.ok();
        } catch (Exception e){
            return Response.err("操作失败，请重试");
        }
    }
}
