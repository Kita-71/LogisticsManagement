package com.babyq.logisticsmanagement.mapper;

import com.babyq.logisticsmanagement.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
// @Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * from user")
    List<User> findAll();
    //将sql语句与函数绑定

    @Insert("insert into `user`(username,`password`,nickname,email,phone,address) VALUES (#{username},#{password},#{nickname},#{email},#{phone},#{address})")
    int insert(User user);
    //@Update("update user set username=#{username},password=#{password},nickname=#{nickname},email=#{email},phone=#{phone},address=#{address} where id=#{id}")
    //无需注解，动态sql语句在resources.mapper.User.xml中写
    int update(User user);

    @Delete("delete from user where id = #{id}")
        //指明deleteById函数中的参数id就是sql语句中的参数#{id}
    Integer deleteById(@Param("id") Integer id);

    @Select("select * from user limit #{rowid}, #{pageSize}")
    List<User> seletPage(Integer rowid, Integer pageSize);

    @Select("select count(*) from user")
    Integer selectTotal();
}
