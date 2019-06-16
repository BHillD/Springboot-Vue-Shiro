package com.example.mapper;

import com.example.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 * @date 2019/5/31
 * @description
 */


public interface InfoMapper {

    List<Menu> getMenusByUserId(@Param("userid") Integer id);

    List<Nation> getAllNations();

    List<Politic> getAllpolitic();

    List<Department> getDepsById(@Param("id") Integer id);

    List<Department> getAllDeps();

    List<Integer> getMenusByRoleId(@Param("id") Integer id);

    List<Menu> menuTree();

    List<Role> getAllRoles();

    void addDep(@Param("department") Department d);

    void delDep(@Param("id") Integer id);

    void addRole(@Param("name") String name);

    void delRole(@Param("id") Integer id);

    List<User> getUsers();

}
