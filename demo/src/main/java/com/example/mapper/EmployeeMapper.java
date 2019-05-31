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

    /**
     *
     * @return 返回所有民族信息
     */
    List<Nation> getAllNations();


    /**
     *
     * @return 返回所有政治面貌信息
     */
    List<PoliticsStatus> getAllPolitics();


    /**
     *
     * @param start 起始位置
     * @param size 页面长度 默认为10
     * @param keywords 模糊查询关键字 默认为空
     * @return 返回由起始位置开始的一页数据
     */
    List<Employee> getEmployeeByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("keywords") String keywords);


    /**
     *
     * @param keywords 查询关键字
     * @return 返回符合查询要求的员工的数据
     */
    Integer getCountByKeywords(@Param("keywords") String keywords);
}
