package com.babyq.logisticsmanagement.controller;

import com.babyq.logisticsmanagement.entity.Order;
import com.babyq.logisticsmanagement.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/UserOrder")
public class OrderController {
    @Autowired(required = false)
    private OrderMapper orderMapper;

    @GetMapping("/pageget")
    public Map<String,Object> getPagedOrder(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        Integer rowid=(pageNum-1)*pageSize;
        Integer total = orderMapper.getOrderCount();
        Map<String,Object>res=new HashMap<>();
        List<Order> paged_order = orderMapper.seletPage(rowid,pageSize);
        res.put("data",paged_order);
        res.put("total",total);
        return res;
    }
}
