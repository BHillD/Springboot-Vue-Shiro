package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.service.MenuService;
import com.example.utlis.CurrentUser;
import com.example.utlis.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date 2019/5/31
 * @description
 */

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    MenuService menuService;

    /**
     *
     * @return 返回当前用户可用的菜单数据
     */
    @GetMapping("/menu")
    public JSONObject menu(){
        return Response.ok(menuService.getMenusByUserId());
    }
}
