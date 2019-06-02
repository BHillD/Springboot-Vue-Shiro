package com.example.service;

import com.example.mapper.MenuMapper;
import com.example.model.Menu;
import com.example.utlis.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @date 2019/5/31
 * @description 菜单相关操作服务
 */


@Service
public class MenuService{

    @Autowired
    MenuMapper menuMapper;

    /**
     * 通过用户ID查询可查看的菜单资源
     * @return 当前用户可用的菜单资源
     */
    public List<Menu> getMenusByUserId() {
        return menuMapper.getMenusByUserId(CurrentUser.getCurrentUser().getId());
    }
}
