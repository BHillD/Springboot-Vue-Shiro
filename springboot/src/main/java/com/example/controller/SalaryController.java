package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.Salary;
import com.example.service.SalaryService;
import com.example.utlis.Response;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author
 * @date 2019/6/3
 * @description 工资相关操作
 */

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    
    /**
     *
     * @return 返回全部工资账套
     */
    @GetMapping("/accountset")
    public ResponseEntity getAllAccountset(){

        return Response.ok(salaryService.getAllaccountset());
    }

    @PostMapping("/accountset")
    public ResponseEntity addAccountset(@RequestBody JSONObject obj){

    }

    @PutMapping("/accountset")
    public ResponseEntity editAccountset(@RequestBody JSONObject obj){

    }

    @DeleteMapping("/ccountset")
    public ResponseEntity delAccountset(@RequestParam("id") String id){

    }

    @PostMapping("/empsalary")
    public ResponseEntity addSalary(@RequestBody JSONObject obj){
        Salary salary = obj.toJavaObject(Salary.class);
        try {
            salaryService.addSalary(salary);
            return Response.ok();
        }catch (Exception e){
            return Response.badReq();
        }
    }



    @PutMapping("/empsalary")
    public ResponseEntity editEmpSalary(@RequestBody JSONObject obj) throws Exception{
        Integer eid = obj.getInteger("eid");
        Integer sid = obj.getInteger("sid");
        if(eid == null || sid == null){
            return Response.badReq();
        }
        try{
            salaryService.editEmpSalary(eid, sid);
            return Response.ok();
        }catch (Exception e){
            return Response.err();
        }
    }
}
