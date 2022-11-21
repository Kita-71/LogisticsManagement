package com.babyq.logisticsmanagement.mapper;

import com.babyq.logisticsmanagement.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface OrderMapper {

    @Select("select count(*) from logistics_order")
    Integer getOrderCount();
    @Select("select * from logistics_order limit #{rowid}, #{pageSize}")
    List<Order> seletPage(Integer rowid, Integer pageSize);
}
