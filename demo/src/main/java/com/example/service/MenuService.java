package com.example.service;

import com.example.mapper.MenuMapper;
import com.example.model.Menu;
import com.example.utiles.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService{

    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenusByUserId() {
        return menuMapper.getMenusByUserId(CurrentUser.getCurrentUser().getId());
    }
}
