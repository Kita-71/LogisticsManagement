package com.babyq.logisticsmanagement.service;

import com.babyq.logisticsmanagement.entity.Order;
import com.babyq.logisticsmanagement.mapper.OrderMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends ServiceImpl <OrderMapper, Order>{
    public  Order getOrder(String orderId) {
        return getById(orderId);//mabatis+提供的函数@Service
    }
}
