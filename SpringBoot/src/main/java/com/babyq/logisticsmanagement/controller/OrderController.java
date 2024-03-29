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
import java.time.LocalDateTime;
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

    @GetMapping("/count")
    public long getCount()
    {
        return orderService.count();
    }

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
        queryWrapper.orderByAsc("state");
        return orderService.page(page,queryWrapper);
    }

    @PostMapping("/newOrUpdateOrder")
    public boolean newOrUpdateOrder(@RequestBody Order order){
        if(order.getOrderId()==null)
            order.setBookTime(LocalDateTime.now());

        return orderService.newOrUpdateOrder(order);
    }
    @PostMapping("/newOrUpdateOrder2")
    public boolean newOrUpdateOrder2(@RequestBody Order order){
        order.setSendTime(LocalDateTime.now());

        return orderService.newOrUpdateOrder(order);
    }
    @PostMapping("/newOrUpdateOrder3")
    public boolean newOrUpdateOrder3(@RequestBody Order order){
        order.setDoneTime(LocalDateTime.now());
        return orderService.newOrUpdateOrder(order);
    }

//    @PostMapping("/registerorder")
//    public boolean registerOrder(@RequestBody String orderId){
//        return orderService.registerOrder(orderId);
//    }

    @GetMapping("/getorder")
    public Order getorder(@RequestParam String orderId)
    {
        return orderService.getOrder(orderId);
    }
    @GetMapping("/checkexist")
    //检查订单是否已经入过库
    public boolean checkexistorder(@RequestParam String orderId,@RequestParam Integer current_site)
    {
        return orderService.checkexistOrder(orderId,current_site);
    }
    @GetMapping("/checkorder")
    //检查订单号是否存在
    public boolean checkorder(@RequestParam String orderId)
    {
        return orderService.checkOrder(orderId);
    }
    @GetMapping("/getsiteordertotal")
    //用于获取本站点的所有待发出快递
    public IPage<Order> getsiteordertotal(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam Integer siteId,@RequestParam(defaultValue = "") String searchMode, @RequestParam(defaultValue = "") String search_input)
    {
        IPage<Order> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Order> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("current_site",siteId);//筛选本站点订单
        if(!search_input.isEmpty())
        {
            if(searchMode.equals("orderId"))
            {
                queryWrapper.eq("orderId",search_input);
            }
            else if (searchMode.equals("goods"))
            {
                queryWrapper.like("goods",search_input);
            }
            else if (searchMode.equals("sender_name"))
            {
                queryWrapper.like("sender_name",search_input);
            }
            else if (searchMode.equals("sender_phone"))
            {
                queryWrapper.eq("sender_phone",search_input);
            }
            else if (searchMode.equals("receiver_name"))
            {
                queryWrapper.like("receiver_name",search_input);
            }
            else if (searchMode.equals("receiver_phone"))
            {
                queryWrapper.eq("receiver_phone",search_input);
            }
        }
        queryWrapper.orderByAsc("state");
        return orderService.page(page,queryWrapper);
    }
    @GetMapping("/getsiteorder")
    //用于获取本站点的所有待发出快递
    public IPage<Order> getsiteorder(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam Integer siteId,@RequestParam(defaultValue = "") String searchMode, @RequestParam(defaultValue = "") String search_input)
    {
        IPage<Order> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Order> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("state","pending_pickup");//筛选待派送订单
        queryWrapper.eq("current_site",siteId);//筛选本站点订单
        if(!search_input.isEmpty())
        {
            if(searchMode.equals("orderId"))
            {
                queryWrapper.eq("orderId",search_input);
            }
            else if (searchMode.equals("goods"))
            {
                queryWrapper.like("goods",search_input);
            }

            else if (searchMode.equals("receiver_name"))
            {
                queryWrapper.like("receiver_name",search_input);
            }
            else if (searchMode.equals("receiver_phone"))
            {
                queryWrapper.eq("receiver_phone",search_input);
            }
            else if (searchMode.equals("dest"))
            {
                queryWrapper.eq("dest",search_input);
            }
        }
        return orderService.page(page,queryWrapper);
    }
    @GetMapping("/getsiteorderdone")
    //用于获取本站点的所有已发出快递
    public IPage<Order> getsiteorderdone(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam Integer siteId,@RequestParam(defaultValue = "") String searchMode, @RequestParam(defaultValue = "") String search_input)
    {
        IPage<Order> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Order> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("state","done");//筛选待派送订单
        queryWrapper.eq("current_site",siteId);//筛选本站点订单
        if(!search_input.isEmpty())
        {
            if(searchMode.equals("orderId"))
            {
                queryWrapper.eq("orderId",search_input);
            }
            else if (searchMode.equals("goods"))
            {
                queryWrapper.like("goods",search_input);
            }
            else if (searchMode.equals("receiver_name"))
            {
                queryWrapper.like("receiver_name",search_input);
            }
            else if (searchMode.equals("receiver_phone"))
            {
                queryWrapper.eq("receiver_phone",search_input);
            }
            else if (searchMode.equals("dest"))
            {
                queryWrapper.like("dest",search_input);
            }
        }
        return orderService.page(page,queryWrapper);
    }
    @GetMapping("/getreserveorder")
    //用于获取本站点的所有预约快递
    public IPage<Order> getreserveorder(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam Integer siteId,@RequestParam(defaultValue = "") String searchMode, @RequestParam(defaultValue = "") String search_input)
    {
        IPage<Order> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Order> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("state","reserve");//筛选待派送订单
        queryWrapper.eq("current_site",siteId);//筛选本站点订单
        if(!search_input.isEmpty())
        {
            if(searchMode.equals("orderId"))
            {
                queryWrapper.eq("orderId",search_input);
            }
            else if (searchMode.equals("goods"))
            {
                queryWrapper.like("goods",search_input);
            }
            else if (searchMode.equals("sender_name"))
            {
                queryWrapper.like("sender_name",search_input);
            }
            else if (searchMode.equals("sender_phone"))
            {
                queryWrapper.eq("sender_phone",search_input);
            }
            else if (searchMode.equals("origin"))
            {
                queryWrapper.like("origin",search_input);
            }
        }
        return orderService.page(page,queryWrapper);
    }

    @DeleteMapping("/delete")
    //删除指定数据
    public boolean deleteUser(@RequestParam String id) {
        return orderService.deleteOrder(id);
    }
    @GetMapping("/pagefilter")
    public IPage<Order> getFilteredOrderPaged(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String searchMode, @RequestParam(defaultValue = "") String search_input ){
        IPage<Order> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Order> queryWrapper= new QueryWrapper<>();

        if(!search_input.isEmpty())
        {
            if(searchMode.equals("orderId"))
            {
                queryWrapper.eq("orderId",search_input);
            }
            else if (searchMode.equals("goods"))
            {
                queryWrapper.like("goods",search_input);
            }
            else if (searchMode.equals("sender_name"))
            {
                queryWrapper.like("sender_name",search_input);
            }
            else if (searchMode.equals("sender_phone"))
            {
                queryWrapper.eq("sender_phone",search_input);
            }
            else if (searchMode.equals("receiver_name"))
            {
                queryWrapper.like("receiver_name",search_input);
            }
            else if (searchMode.equals("receiver_phone"))
            {
                queryWrapper.eq("receiver_phone",search_input);
            }
        }
        return orderService.page(page,queryWrapper);
    }
}
