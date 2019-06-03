package com.example.mapper;

import com.example.model.Salary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 * @date 2019/6/3
 * @description
 */
public interface SalaryMapper {
    List<Salary> getAllSalaries();
    void editEmpSalary(@Param("eid") Integer eid, @Param("sid") Integer sid);
}
