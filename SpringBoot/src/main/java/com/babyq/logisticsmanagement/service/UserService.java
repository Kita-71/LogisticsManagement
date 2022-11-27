package com.babyq.logisticsmanagement.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.babyq.logisticsmanagement.entity.User;
import com.babyq.logisticsmanagement.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sun.util.resources.LocaleData;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",passwd);
        User user=this.getOne(queryWrapper);
        if(user==null)
            return false;
        else
            return true;
    }

    public Map<String,Boolean> checkAccess(String username, String passwd, String require) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        Map<String,Boolean>AnswerMap=new HashMap<>();
        queryWrapper.eq("username",username);
        User user1=this.getOne(queryWrapper);
        if(user1==null)
            AnswerMap.put("user",false);
        else
            AnswerMap.put("user",true);

        queryWrapper.eq("password",passwd);
        User user2=this.getOne(queryWrapper);
        if(user2==null)
            AnswerMap.put("password",false);
        else
            AnswerMap.put("password",true);

        queryWrapper.eq("permission",require);
        User user3=this.getOne(queryWrapper);
        if(user3==null)
            AnswerMap.put("permission",false);
        else
            AnswerMap.put("permission",true);

        return AnswerMap;
    }

    public Map<String, Boolean> signUp(User user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        Map<String,Boolean>AnswerMap=new HashMap<>();
        queryWrapper.eq("username",user.getUsername());
        User user_check=this.getOne(queryWrapper);
        if(user_check==null)
        {
            AnswerMap.put("user_exist",false);
            user.setNickname("BabyQNewUser");
            user.setPermission("commonUser");
            user.setCreateTime(LocalDateTime.now());
            AnswerMap.put("signup_success",this.save(user));
        }
        else
        {
            AnswerMap.put("user_exist",true);
            AnswerMap.put("signup_success",false);
        }
        return AnswerMap;
    }
}
