package com.babyq.logisticsmanagement.service;

import com.babyq.logisticsmanagement.entity.Order;
import com.babyq.logisticsmanagement.entity.User;
import com.babyq.logisticsmanagement.mapper.OrderMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends ServiceImpl <OrderMapper, Order>{
    public  Order getOrder(String orderId) {
        return getById(orderId);//mabatis+提供的函数@Service
    }

    public boolean newOrUpdateOrder(Order order) {
        return saveOrUpdate(order);
    }

    public boolean deleteOrder(String id) {
        return removeById(id);
    }

    public boolean checkOrder(String orderId) {
        QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("orderId",orderId);
        Order order=getOne(queryWrapper);
        if(order==null)
            return false;
        else
            return true;

    }

    public boolean checkexistOrder(String orderId,Integer current_site) {
        QueryWrapper<Order> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("orderId",orderId);
        queryWrapper.eq("current_site",current_site);
        queryWrapper.eq("state","pending_pickup");
        Order order=getOne(queryWrapper);
        if(order==null)
            return false;
        else
            return true;
    }


//    public boolean registerOrder(String orderId) {
//
//    }
}
