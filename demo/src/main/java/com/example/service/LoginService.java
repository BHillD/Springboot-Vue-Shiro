package com.example.service;

import com.alibaba.fastjson.JSONObject;

public interface LoginService {
     JSONObject login(String username, String password);
     JSONObject logout();
}
