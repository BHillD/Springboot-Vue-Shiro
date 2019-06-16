package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.InfoMapper;
import com.example.model.Menu;
import com.example.service.InfoService;
import com.example.service.UserService;
import com.example.utlis.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    InfoService infoService;

    @GetMapping("/users")
    public JSONObject getAllUsers(){
        return Response.ok(infoService.getAllUsers());
    }

    @GetMapping("/roles")
    public JSONObject getAllRoles(){
        return Response.ok(infoService.getAllRoles());
    }

    @GetMapping("/menutree/{id}")
    public JSONObject menuTree(@PathVariable("id") Integer id){
        List<Integer> menus = infoService.getMenusByRoleId(id);
        List<Menu> menutree = infoService.getMenuTree();
        JSONObject obj = new JSONObject();
        System.out.println(menutree.toString());
        System.out.println(menus.toString());
        obj.put("menus",menus);
        obj.put("tree",menutree);
        return obj;
    }

    @PostMapping("/role")
    public JSONObject addRole(@RequestBody JSONObject obj){
        infoService.addRole(obj);
        return Response.ok();
    }

    @DeleteMapping("/role/{id}")
    public JSONObject delRole(@PathVariable Integer id){
        infoService.delRole(id);
        return Response.ok();
    }
}
