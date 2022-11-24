package com.babyq.logisticsmanagement.mapper;

import com.babyq.logisticsmanagement.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


// @Mapper
//要继承BaseMapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select count(*) from logistics_order")
    Integer getOrderCount();
    @Select("select * from logistics_order limit #{rowid}, #{pageSize}")
    List<Order> seletPage(Integer rowid, Integer pageSize);
}
