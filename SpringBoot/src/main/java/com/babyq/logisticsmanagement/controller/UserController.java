package com.babyq.logisticsmanagement.controller;

import com.babyq.logisticsmanagement.entity.User;
import com.babyq.logisticsmanagement.mapper.UserMapper;
import com.babyq.logisticsmanagement.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    //使得springboot管理UserMapper
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    @PostMapping
    //新增或更新
    public boolean sava(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //    @GetMapping
//    public List<User> findAll(){
//        List<User> all = userMapper.findAll();
//        return all;
//    }
    @GetMapping
    //获取所有数据
    public List<User> findAll() {
        return userService.getList();
    }

    @DeleteMapping("/{id}")
    //删除指定数据
    public boolean delete(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    //    @GetMapping("/page")
//    public Map<String,Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
//        Integer rowid=(pageNum-1)*pageSize;
//        Integer total = userMapper.selectTotal();
//        Map<String,Object>res=new HashMap<>();
//        List<User> paged_user = userMapper.seletPage(rowid,pageSize);
//        res.put("data",paged_user);
//        res.put("total",total);
//        return res;
//    }
    @GetMapping("/page")
    //分页查询
    public IPage findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam(defaultValue = "") String username,@RequestParam(defaultValue = "") String nickname) {
        IPage<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User>queryWrapper= new QueryWrapper<>();
        queryWrapper.like("username",username);
        queryWrapper.like("nickname",nickname);
        return userService.page(page,queryWrapper);

    }
}
