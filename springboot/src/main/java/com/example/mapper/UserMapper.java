package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 * @date 2019/5/31
 * @description
 */


public interface UserMapper {

    List<User> getUsers();

    User getUserByUsername(@Param("username") String username);

    void addRole();

    void addUser(@Param("usernaeme") String username,@Param("password") String password,@Param("name") String name,@Param("face") String face);

}
