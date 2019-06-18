package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.InfoMapper;
import com.example.model.Menu;
import com.example.service.InfoService;
import com.example.service.UserService;
import com.example.utlis.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getAllUsers(){
        return Response.ok(infoService.getAllUsers());
    }

    @GetMapping("/roles")
    public ResponseEntity getAllRoles(){
        return Response.ok(infoService.getAllRoles());
    }

    @GetMapping("/menutree")
    public ResponseEntity menuTree(@RequestParam("id") Integer id){
        List<Integer> menus = infoService.getMenusByRoleId(id);
        List<Menu> menutree = infoService.getMenuTree();
        JSONObject obj = new JSONObject();
        obj.put("tree",menutree);
        obj.put("menus",menus);
        return Response.ok(obj);
    }

    @PostMapping("/role")
    public ResponseEntity addRole(@RequestBody JSONObject obj){
        String name = obj.getString("name");
        if(name == null){
            return Response.badReq();
        }
        try {
            infoService.addRole(name);
            return Response.ok();
        }catch (Exception e){
            return Response.err();
        }
    }

    @DeleteMapping("/role")
    public ResponseEntity delRole(@RequestParam("id") Integer id){
        if(id == null){
            return Response.badReq();
        }
        try {
            infoService.delRole(id);
            return Response.ok();
        }catch (Exception e){
            return  Response.err();
        }
    }
}
