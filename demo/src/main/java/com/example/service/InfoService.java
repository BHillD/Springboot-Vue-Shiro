package com.example.service;

import com.example.mapper.InfoMapper;
import com.example.model.Menu;
import com.example.model.Nation;
import com.example.model.Politic;
import com.example.model.Salary;
import com.example.utlis.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
