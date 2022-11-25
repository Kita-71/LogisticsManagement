package com.babyq.logisticsmanagement.service;

import com.babyq.logisticsmanagement.entity.User;
import com.babyq.logisticsmanagement.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl <UserMapper, User>{
    public boolean saveUser(User user) {
        return saveOrUpdate(user);//mabatis+提供的函数
    }

    public List<User> getList() {
        return  list();
    }

    public boolean deleteUser(Integer id) {
        return removeById(id);
    }
    public boolean checkPasswd(String username,String passwd){

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("username",username);
        queryWrapper.like("password",passwd);
        User user=this.getOne(queryWrapper);
        if(user==null)
            return false;
        else
            return true;
    }
}
