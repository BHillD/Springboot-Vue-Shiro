package com.example.mapper;

import com.example.model.Salary;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authc.Account;

import java.util.List;

/**
 * @author
 * @date 2019/6/3
 * @description
 */
public interface SalaryMapper {
    void addSalary(@Param("salary") Salary salary);

    void editSalary(@Param("salary") Salary salary);

    List<Salary> getAllSalaries();

    void editEmpSalary(@Param("eid") Integer eid, @Param("sid") Integer sid);
}
