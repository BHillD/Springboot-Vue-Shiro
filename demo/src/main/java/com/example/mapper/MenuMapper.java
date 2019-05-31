package com.example.mapper;

import com.example.model.Menu;

import java.util.List;

/**
 * @author
 * @date 2019/5/31
 * @description
 */


public interface MenuMapper {
    List<Menu> getMenusByUserId(Integer id);
}
