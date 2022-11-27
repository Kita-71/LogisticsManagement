package com.babyq.logisticsmanagement.service;

import com.babyq.logisticsmanagement.entity.Information;
import com.babyq.logisticsmanagement.entity.Site;
import com.babyq.logisticsmanagement.mapper.InformationMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationService extends ServiceImpl <InformationMapper, Information>{

}
