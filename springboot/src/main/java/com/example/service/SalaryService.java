package com.example.service;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.SalaryMapper;
import com.example.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Salary> getAllaccountset(){
        return salaryMapper.getAllAccountset();
    }

    @Transactional
    public void addSalary(Salary salary) throws Exception{
        salaryMapper.addSalary(salary);
    }

    /**
     * 返回员工工资信息
     */
    @Transactional
    public void editEmpSalary(Integer eid, Integer sid) throws Exception{
        salaryMapper.editEmpSalary(eid, sid);
    }
}
