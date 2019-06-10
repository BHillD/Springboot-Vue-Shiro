package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date 2019/6/5
 * @description
 */
@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public JSONObject getUsers(){
        JSONObject obj = new JSONObject();
        obj.put("users", obj);
        return obj;
    }
}
