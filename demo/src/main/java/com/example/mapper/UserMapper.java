package com.example.mapper;

import com.example.model.User;

/**
 * @author
 * @date 2019/5/31
 * @description
 */


public interface UserMapper {

    User getUserByUsername(String username);
}
