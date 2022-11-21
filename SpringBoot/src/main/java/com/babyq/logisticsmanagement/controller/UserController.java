package com.babyq.logisticsmanagement.controller;

import com.babyq.logisticsmanagement.entity.User;
import com.babyq.logisticsmanagement.mapper.UserMapper;
import com.babyq.logisticsmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    //使得springboot管理UserMapper
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    //新增或更新
    @PostMapping
    public Integer sava(@RequestBody User user){
        return userService.save(user);
    }
    //获取所有数据
    @GetMapping
    public List<User> findAll(){
        List<User> all = userMapper.findAll();
        return all;
    }
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);
    }
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        Integer rowid=(pageNum-1)*pageSize;
        Integer total = userMapper.selectTotal();
        Map<String,Object>res=new HashMap<>();
        List<User> paged_user = userMapper.seletPage(rowid,pageSize);
        res.put("data",paged_user);
        res.put("total",total);
        return res;
    }
//    @PostMapping("sign")
//    public Map<Integer,Integer> signin(@RequestBody UserDTO userDTO){
//        String username=userDTO.getUsername();
//        String password=userDTO.getPassword();
//
//        return userService.signin(userDTO);
//    }
}
