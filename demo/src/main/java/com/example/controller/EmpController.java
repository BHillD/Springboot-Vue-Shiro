package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.EmpSalary;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.example.utlis.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
     * @return 返回当前页的员工数据
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
    @Transactional
    public JSONObject addEmp(@RequestBody JSONObject obj) throws Exception{
        try{
            employeeService.addEmployee(obj);
            return Response.ok();
        }catch (Exception e){
            return Response.err("操作失败,请重试");
        }
    }

    /**
     * 删除员工信息
     * @param obj 含有员工身份信息的JSON对象
     * @return
     *
     */
    @PostMapping("/delete")
    @Transactional
    public JSONObject delEmp(@RequestBody JSONObject obj) throws Exception{
        try{
            employeeService.deleteEmployee(obj);
            return Response.ok();
        }catch (Exception e){
            return Response.err("操作失败,请重试");
        }
    }

    /**
     * 修改员工信息
     * @param obj 所选员工的修改信息的JSON对象
     * @return
     *
     */
    @PostMapping("/edit")
    @Transactional
    public JSONObject editEmp(@RequestBody JSONObject obj) throws Exception{
        try{
            employeeService.editEmployee(obj);
            return Response.ok();
        }catch (Exception e){
            return Response.err("操作失败,请重试");
        }
    }

    /**
     * 返回员工工资信息
     * @param page 当前页数
     * @param size 每页数量
     * @return
     */
    @GetMapping("/empsalary")
    public JSONObject getEmpSalariesByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        List<EmpSalary> empSalariesByPage = employeeService.getEmpSalariesByPage(page, size);
        Integer count = employeeService.getCountByKeywords("");
        JSONObject obj = new JSONObject();
        obj.put("empsalaries", empSalariesByPage);
        obj.put("count", count);
        return obj;
    }
}
