package com.example.service;

import com.example.mapper.UserMapper;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
