package com.babyq.logisticsmanagement.controller;

import com.babyq.logisticsmanagement.entity.Order;
import com.babyq.logisticsmanagement.entity.User;
import com.babyq.logisticsmanagement.mapper.UserMapper;
import com.babyq.logisticsmanagement.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    //使得springboot管理UserMapper
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    @PostMapping("/changeInfo")
    //新增或更新
    public boolean changeInfo(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/checkPasswd")
    public boolean checkPasswd(@RequestBody Map<String, String> userMap){
        String username=  userMap.get("username");
        String passwd=  userMap.get("passwd");
        return userService.checkPasswd(username,passwd);
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
    @DeleteMapping("/delete")
    //删除指定数据
    public boolean deleteUser(@RequestParam Integer id) {
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

    @GetMapping("/get")
    public User getUser(@RequestParam String username)
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("username",username);
        return userService.getOne(queryWrapper);
    }

    @GetMapping("/pagefilter")
    public IPage<User> getFilteredUserPaged(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String searchMode, @RequestParam(defaultValue = "") String search_input ){
        IPage<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper= new QueryWrapper<>();

        if(search_input.isEmpty()==false)
        {
            if(searchMode.equals("username"))
            {
                queryWrapper.like("username",search_input);
            }
            else if (searchMode.equals("phone"))
            {
                queryWrapper.like("phone",search_input);
            }
            else if (searchMode.equals("email"))
            {
                queryWrapper.like("email",search_input);
            }
            else if (searchMode.equals("id"))
            {
                //queryWrapper.like("id",search_input);
                queryWrapper.eq("id",search_input);
            }
        }
        return userService.page(page,queryWrapper);
    }

}
