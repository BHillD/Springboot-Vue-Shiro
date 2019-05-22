package com.example.service;

import com.example.mapper.EmployeeMapper;
import com.example.model.Employee;
import com.example.model.Nation;
import com.example.model.PoliticsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService{

    @Autowired
    EmployeeMapper employeeMapper;



    public List<Nation> getAllNations() {
        return employeeMapper.getAllNations();
    }


    public List<PoliticsStatus> getAllPolitics() {
        return employeeMapper.getAllPolitics();
    }


    public List<Employee> getEmployeeByPage(Integer page, Integer size, String keywords) {
        Integer start = (page - 1) * size;
        return employeeMapper.getEmployeeByPage(start, size, keywords);
    }


    public Integer getCountByKeywords(String keywords) {
        return employeeMapper.getCountByKeywords(keywords);
    }
}
