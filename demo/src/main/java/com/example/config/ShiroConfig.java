package com.example.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @date 2019/5/31
 * @description Shiro配置类
 */

@Configuration
public class ShiroConfig {

    @Bean("realm")
    public Realm getRealm() {
        return new Realm();
    }

    @Bean("defaultSecurityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("realm") Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("defaultSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }
}
