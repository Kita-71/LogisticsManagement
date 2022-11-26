package com.babyq.logisticsmanagement.controller;

import com.babyq.logisticsmanagement.entity.Order;
import com.babyq.logisticsmanagement.entity.User;
import com.babyq.logisticsmanagement.mapper.OrderMapper;
import com.babyq.logisticsmanagement.service.OrderService;
import com.babyq.logisticsmanagement.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.nimbus.State;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired(required = false)
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/pageGetByPhone")
    public IPage<Order> getPagedOrderByPhone(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String phone, @RequestParam String state,
                                             @RequestParam boolean orderMode,@RequestParam String searchMode,@RequestParam String search_input ,
                                             @RequestParam Integer receiver_uid,@RequestParam Integer sender_uid
                                            ){
        IPage<Order> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Order> queryWrapper= new QueryWrapper<>();
        if(orderMode==false)
        {
            queryWrapper.and(qw->qw.like("receiver_phone",phone).or().like("receiver_uid",receiver_uid));
        }
        else
        {
            queryWrapper.and(qw->qw.like("sender_phone",phone).or().like("sender_uid",sender_uid));
        }
        if(state.equals("all")==false)
        {
            queryWrapper.like("state",state);
        }
        if(search_input.isEmpty()==false)
        {
            if(searchMode.equals("origin"))
            {
                queryWrapper.like("origin",search_input);
            }
            else if (searchMode.equals("dest"))
            {
                queryWrapper.like("dest",search_input);
            }
            else if (searchMode.equals("time"))
            {
                queryWrapper.like("orderId",search_input);
            }
        }

        return orderService.page(page,queryWrapper);
    }

    @PostMapping("/newOrUpdateOrder")
    public boolean newOrUpdateOrder(@RequestBody Order order){
        return orderService.newOrUpdateOrder(order);
    };

    @GetMapping("/getorder")
    public Order getorder(@RequestParam String orderId)
    {
        return orderService.getOrder(orderId);
    };
}
