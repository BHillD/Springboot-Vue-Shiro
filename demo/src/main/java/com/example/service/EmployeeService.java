package com.example.service;

import com.example.model.Employee;
import com.example.model.Nation;
import com.example.model.PoliticsStatus;

import java.util.List;

public interface EmployeeService{
    List<Nation> getAllNations();

    List<PoliticsStatus> getAllPolitics();

    List<Employee> getEmployeeByPage(Integer page, Integer size, String keywords);

    Integer getCountByKeywords(String keywords);
}
