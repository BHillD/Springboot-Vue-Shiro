package com.example.config;

import com.example.model.User;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 * @date 2019/5/31
 * @description
 */

public class Realm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override


    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        User user = userService.getUserByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(user.getRole().getName());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken args) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) args;
        String username = token.getUsername();
        User user = userService.getUserByUsername(username);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                this.getName()
        );
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
        return info;
    }
}
