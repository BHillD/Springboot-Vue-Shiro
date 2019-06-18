package com.example.service;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.InfoMapper;
import com.example.model.*;
import com.example.utlis.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 * @date 2019/5/31
 * @description 有关信息相关操作服务
 */


@Service
public class InfoService{

    @Autowired
    InfoMapper infoMapper;

    /**
     * 通过用户ID查询可查看的菜单资源
     * @return 当前用户可用的菜单资源
     */
    public List<Menu> getMenusByUserId() {
        return infoMapper.getMenusByUserId(CurrentUser.getCurrentUser().getId());
    }

    public List<Nation> getAllNations(){
        return infoMapper.getAllNations();
    }

    public List<Politic> getAllPolitics(){
        return infoMapper.getAllpolitic();
    }

    public List<Department> getDepsById(Integer id){
        return infoMapper.getDepsById(id);
    }

    public List<Department> getAllDeps(){
        return infoMapper.getAllDeps();
    }

    public List<Integer> getMenusByRoleId(Integer id){
        return infoMapper.getMenusByRoleId(id);
    }

    public List<Role> getAllRoles(){
        return infoMapper.getAllRoles();
    }

    public List<Menu> getMenuTree(){
        return infoMapper.menuTree();
    }

    @Transactional
    public void addDep(Department d) throws Exception{
        infoMapper.addDep(d);
    }

    @Transactional
    public void delDep(Integer id) throws Exception{
        infoMapper.delDep(id);
    }

    @Transactional
    public void addRole(String name) throws Exception{
        infoMapper.addRole(name);
    }

    @Transactional
    public void delRole(Integer id) throws Exception{
        infoMapper.delRole(id);
    }

    public List<User> getAllUsers(){
        return infoMapper.getUsers();
    }

}
