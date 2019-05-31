package com.example.mapper;

import com.example.model.User;

/**
 * @author
 * @date 2019/5/31
 * @description
 */


public interface UserMapper {
    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return 返回用户数据
     */
    User getUserByUsername(String username);
}
