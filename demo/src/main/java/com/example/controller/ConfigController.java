package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.Menu;
import com.example.service.MenuService;
import com.example.utiles.CurrentUser;
import com.example.utiles.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/menu")
    public JSONObject Menu(){
        return Response.ok(menuService.getMenuByUserId());
    }

    @RequestMapping("/user")
    public JSONObject User(){
        return Response.ok(CurrentUser.getCurrentUser());
    }
}
