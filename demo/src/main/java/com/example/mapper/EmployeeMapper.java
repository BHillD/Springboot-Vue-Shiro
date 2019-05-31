package com.example.mapper;

import com.example.model.Employee;
import com.example.model.Nation;
import com.example.model.PoliticsStatus;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 * @date 2019/5/31
 * @description
 */


import java.util.List;

public interface EmployeeMapper {

    List<Nation> getAllNations();

    List<PoliticsStatus> getAllPolitics();

    List<Employee> getEmployeeByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("keywords") String keywords);

    Integer getCountByKeywords(@Param("keywords") String keywords);
}
