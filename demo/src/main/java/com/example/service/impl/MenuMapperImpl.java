package com.example.service.impl;

import com.example.mapper.MenuMapper;
import com.example.model.Menu;
import com.example.service.MenuService;
import com.example.utiles.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuMapperImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByUserId() {
        return menuMapper.getMenusByUserId(CurrentUser.getCurrentUser().getId());
    }
}
