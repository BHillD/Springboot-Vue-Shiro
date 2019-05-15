package com.example.service;

import com.example.model.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuByUserId();
}
