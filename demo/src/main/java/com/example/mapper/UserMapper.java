package com.example.mapper;

import com.example.model.User;

public interface UserMapper {
    User getUserByUsername(String username);
}
