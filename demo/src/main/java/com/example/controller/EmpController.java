package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmpController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/basicinfo")
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

    @GetMapping("/basicdata")
    public JSONObject getAllNations() {
        JSONObject obj = new JSONObject();
        obj.put("nations", employeeService.getAllNations());
        obj.put("politics", employeeService.getAllPolitics());
        return obj;
    }

}
