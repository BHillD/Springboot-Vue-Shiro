package com.example.service;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.SalaryMapper;
import com.example.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @date 2019/6/3
 * @description
 */
@Service
public class SalaryService {
    @Autowired
    SalaryMapper salaryMapper;

    /**
     * 返回所有工资账套信息
     * @return
     */
    public List<Salary> getAllSalaries(){
        return salaryMapper.getAllSalaries();
    }

    public void addSalary(JSONObject obj){
        Salary salary = obj.toJavaObject(Salary.class);
        salaryMapper.addSalary(salary);
    }

    /**
     * 返回员工工资信息
     * @param eid 员工ID
     * @param sid 工资账套ID
     */
    public void editEmpSalary(Integer eid,Integer sid){
        salaryMapper.editEmpSalary(eid, sid);
    }
}
