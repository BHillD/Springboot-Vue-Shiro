package com.example.service;

import com.example.mapper.UserMapper;
import com.example.model.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author
 * @date 2019/5/31
 * @description 用户相关操作服务
 */

@Service
public class UserService{

    @Autowired
    UserMapper userMapper;

    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Transactional
    public void registUser(String username, String password, String name) throws Exception{
        Md5Hash md5Hash = new Md5Hash(username,password,1);
        password = md5Hash.toString();
        String face = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559736732420&di=ebd84f4d2e8a20946b9064be502560f8&imgtype=0&src=http%3A%2F%2Fwww.sccf.org.cn%2FUpLoads%2FHeadImg%2FUserHeadImg.png";
        userMapper.addUser(username, password, name,face);
        userMapper.addRole();
    }

    public List<User> getUsers(){
        return userMapper.getUsers();
    }
}
