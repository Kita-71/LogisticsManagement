package com.babyq.logisticsmanagement.controller;
import com.babyq.logisticsmanagement.entity.*;
import com.babyq.logisticsmanagement.entity.Site;
import com.babyq.logisticsmanagement.mapper.InformationMapper;
import com.babyq.logisticsmanagement.mapper.UserMapper;
import com.babyq.logisticsmanagement.service.InformationService;
import com.babyq.logisticsmanagement.service.SiteService;
import com.babyq.logisticsmanagement.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InformationController {
    @Autowired(required = false)
    private InformationMapper informationMapper;
    @Autowired
    private InformationService informationService;
    @GetMapping("/all")
    public List<Information> getAll()
    {
        return informationService.list();
    }
    @PostMapping("/new")
    public boolean newOne(@RequestBody Information information)
    {
        return informationService.saveOrUpdate(information);
    }


    @DeleteMapping("/delete")
    //删除指定数据
    public boolean deleteInfo(@RequestParam Integer id) {
        return informationService.removeById(id);
    }
}
