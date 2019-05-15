package com.example.controller;

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
    public Map<String, Object> getEmployeeByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String keywords
        ){
        Map<String, Object> map = new HashMap<>();
        List<Employee> employeeByPage = employeeService.getEmployeeByPage(page, size, keywords);
        Integer count = employeeService.getCountByKeywords(keywords);
        map.put("emps", employeeByPage);
        map.put("count", count);
        return map;
    }

    @GetMapping("/basicdata")
    public Map<String, Object> getAllNations() {
        Map<String, Object> map = new HashMap<>();
        map.put("nations", employeeService.getAllNations());
        map.put("politics", employeeService.getAllPolitics());
        return map;
    }

}
