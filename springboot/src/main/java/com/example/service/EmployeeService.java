package com.example.service;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.EmployeeMapper;
import com.example.mapper.SalaryMapper;
import com.example.model.EmpSalary;
import com.example.model.Employee;
import com.example.model.Salary;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 * @date 2019/5/31
 * @description  员工相关操作服务
 */


@Service
public class EmployeeService{

    @Autowired
    EmployeeMapper employeeMapper;


    /**
     *
     * @param page 当前页数
     * @param size 页面长度 默认为10
     * @param keywords 模糊查询关键字 默认为空
     * @return 返回由当前页的员工数据
     */
    public List<Employee> getEmployeeByPage(Integer page, Integer size, String keywords) {
        Integer start = (page - 1) * size;
        System.out.println(keywords);
        return employeeMapper.getEmployeeByPage(start, size, keywords);
    }


    /**
     *
     * @param keywords 模糊查询关键字 默认为空
     * @return 返回员工信息总页数
     */
    public Integer getCountByKeywords(String keywords) {

        return employeeMapper.getCountByKeywords(keywords);
    }

    /**
     * 添加新员工信息
     * @param e 新员工信息的实体对象
     */
    @Transactional
    public void addEmployee(Employee e) throws Exception{
        employeeMapper.addEmployee(e);
    }

    /**
     * 删除员工信息
     * @param
     */
    @Transactional
    public void deleteEmployee(String[] idCards) throws Exception{
        employeeMapper.deleteEmployee(idCards);
    }

    /**
     * 修改员工信息
     * @param obj 所选员工的修改信息的JSON对象
     */
    @Transactional
    public void editEmployee(JSONObject obj) throws Exception{
        Employee e = obj.toJavaObject(Employee.class);
        System.out.println(e.toString());
        employeeMapper.editEmployee(e);
    }

    /**
     *
     * @param page 当前页数
     * @param size 每页数量
     * @return
     */
    public List<EmpSalary> getEmpSalariesByPage(Integer page, Integer size) {
        Integer start = (page - 1) * size;
        return employeeMapper.getEmpSalariesByPage(start, size);
    }

}
