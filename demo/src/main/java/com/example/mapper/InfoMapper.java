package com.example.mapper;

import com.example.model.Menu;
import com.example.model.Nation;
import com.example.model.Politic;

import java.util.List;

/**
 * @author
 * @date 2019/5/31
 * @description
 */


public interface InfoMapper {

    List<Menu> getMenusByUserId(Integer id);

    List<Nation> getAllNations();

    List<Politic> getAllpolitic();


}
