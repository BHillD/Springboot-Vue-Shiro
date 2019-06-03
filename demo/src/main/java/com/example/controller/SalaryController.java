package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.service.SalaryService;
import com.example.utlis.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    public JSONObject getAllSalaries(){
        JSONObject obj = new JSONObject();
        obj.put("salaries", salaryService.getAllSalaries());
        return obj;
    }

    /**
     *
     * @param eid 员工ID
     * @param sid 工资账套ID
     * @return
     *
     */
    @GetMapping("/edit")
    @Transactional
    public JSONObject editEmpSalary(
            @RequestParam("eid") Integer eid,
            @RequestParam("sid") Integer sid
    ) throws Exception{
        try{
            salaryService.editEmpSalary(eid, sid);
            return Response.ok();
        }catch (Exception e){
            return Response.err("操作失败,请重试");
        }
    }
}
