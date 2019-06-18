package com.example.utlis;

import com.example.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @author
 * @date 2019/5/31
 * @description 用户工具类
 */

public class CurrentUser {

    public static String USER = "user";

    public static User getCurrentUser(){
        return (User) SecurityUtils.getSubject().getSession().getAttribute(USER);
    }

}
