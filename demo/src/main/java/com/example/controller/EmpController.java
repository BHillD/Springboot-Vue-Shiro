package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.Employee;
import com.example.service.EmployeeService;
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
     *
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
     * @return 返回所有民族和政治面貌信息
     */
    @GetMapping("/data")
    public JSONObject getAllData() {
        JSONObject obj = new JSONObject();
        obj.put("nations", employeeService.getAllNations());
        obj.put("politics", employeeService.getAllPolitics());
        return obj;
    }


    /**
     *
     * @param obj 含有新用户全部信息的JSON对象
     * @return
     */
    @GetMapping("/add")
    public JSONObject addInfo(@RequestBody JSONObject obj){

        return null;
    }

}
