package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.example.utlis.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  @author
 *  @date 2019/5/31
 *  @description
 */


@RestController
@RequestMapping("/employee")
public class EmpController {

    @Autowired
    EmployeeService employeeService;

    /**
     * @param page  当前页数 默认为1
     * @param size  每页的长度 默认为10
     * @param keywords  用于查询的关键字 默认为空
     * @return 返回当前页的数据
     */
    @GetMapping("/basinfo")
    public JSONObject getEmployeeByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String keywords
        ){
        List<Employee> employeeByPage = employeeService.getEmployeeByPage(page, size, keywords);
        Integer count = employeeService.getCountByKeywords(keywords);
        JSONObject obj = new JSONObject();
        obj.put("emps", employeeByPage);
        obj.put("count", count);
        return obj;
    }


    /**
     *
     * @param obj 含有新用户全部信息的JSON对象
     * @return
     */
    @PostMapping("/add")
    public JSONObject addEmp(@RequestBody JSONObject obj){
        employeeService.addEmployee(obj);
        return Response.ok();
    }

    @PostMapping("/delete")
    public JSONObject delEmp(@RequestBody JSONObject obj){
        employeeService.deleteEmployee(obj);
        return Response.ok();
    }

    @PostMapping("/edit")
    public JSONObject editEmp(@RequestBody JSONObject obj){
        employeeService.editEmployee(obj);
        return Response.ok();
    }

}
