package com.example.mapper;

import com.example.model.EmpSalary;
import com.example.model.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 * @date 2019/5/31
 * @description
 */



import java.util.List;

public interface EmployeeMapper {


    List<Employee> getEmployeeByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("keywords") String keywords);

    Integer getCountByKeywords(@Param("keywords") String keywords);

    void addEmployee(@Param("employee") Employee e);

    void deleteEmployee(@Param("id") String[] idCards);

    void editEmployee(@Param("employee") Employee e);

    List<EmpSalary> getEmpSalariesByPage(@Param("start") Integer start, @Param("size") Integer size);


}
