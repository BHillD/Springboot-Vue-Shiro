package com.example.utiles;

import com.example.model.User;
import org.apache.shiro.SecurityUtils;

public class CurrentUser {
    public static User getCurrentUser(){
        return (User)SecurityUtils.getSubject().getSession().getAttribute("user");
    }
}
