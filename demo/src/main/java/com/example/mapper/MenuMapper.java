package com.example.mapper;

import com.example.model.Menu;

import java.util.List;

/**
 * @author
 * @date 2019/5/31
 * @description
 */


public interface MenuMapper {

    /**
     * 通过用户ID查询菜单
     * @param id 用户ID
     * @return 返回用户可查看的菜单资源
     */
    List<Menu> getMenusByUserId(Integer id);
}
