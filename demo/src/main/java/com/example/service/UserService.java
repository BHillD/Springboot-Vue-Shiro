package com.example.service;

import com.example.mapper.UserMapper;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService{

    @Autowired
    UserMapper userMapper;


    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
