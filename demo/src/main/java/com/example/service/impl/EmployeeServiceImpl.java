package com.example.service.impl;

import com.example.mapper.EmployeeMapper;
import com.example.model.Employee;
import com.example.model.Nation;
import com.example.model.PoliticsStatus;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


    @Override
    public List<Nation> getAllNations() {
        return employeeMapper.getAllNations();
    }

    @Override
    public List<PoliticsStatus> getAllPolitics() {
        return employeeMapper.getAllPolitics();
    }

    @Override
    public List<Employee> getEmployeeByPage(Integer page, Integer size, String keywords) {
        Integer start = (page - 1) * size;
        return employeeMapper.getEmployeeByPage(start, size, keywords);
    }

    @Override
    public Integer getCountByKeywords(String keywords) {
        return employeeMapper.getCountByKeywords(keywords);
    }
}
