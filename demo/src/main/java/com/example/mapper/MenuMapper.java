package com.example.mapper;

import com.example.model.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> getMenusByUserId(Integer id);
}
