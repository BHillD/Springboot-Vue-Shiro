package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.Department;
import com.example.service.InfoService;
import com.example.utlis.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @date 2019/5/31
 * @description
 */

@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;

    /**
     *
     * @return 返回当前用户可用的菜单数据
     */
    @GetMapping("/menu")
    public ResponseEntity menu(){
        return Response.ok(infoService.getMenusByUserId());
    }

    @GetMapping("/nations")
    public ResponseEntity getAllNations(){
        return Response.ok(infoService.getAllNations());
    }

    @GetMapping("/politics")
    public  ResponseEntity getAllPolitics(){
        return Response.ok(infoService.getAllPolitics());
    }

    @GetMapping("/dep")
    public  ResponseEntity getDepsById(@RequestParam("id") Integer id){
        return Response.ok(infoService.getDepsById(id));
    }

    @GetMapping("/deps")
    public ResponseEntity getAllDeps(){
        return Response.ok(infoService.getAllDeps());
    }

    @PostMapping("/dep")
    public ResponseEntity addDep(@RequestBody JSONObject obj){
        Department d = obj.toJavaObject(Department.class);
        try {
            infoService.addDep(d);
            return Response.ok();
        }catch (Exception e){
            return Response.badReq();
        }
    }

    @DeleteMapping("/dep")
    public ResponseEntity delDep(@RequestParam("id") Integer id){
        if(id == null){
            return Response.badReq();
        }
        try {
            infoService.delDep(id);
            return Response.ok();
        }catch (Exception e){
            return Response.err();
        }
    }
}
