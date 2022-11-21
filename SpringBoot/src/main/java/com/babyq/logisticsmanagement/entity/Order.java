package com.babyq.logisticsmanagement.entity;

import lombok.Data;

@Data
public class Order {
    //实体类的变量名要和数据库中的变量名一致，否则无法读取。顺序无所谓
    private String order_id;
    private String origin;
    private String dest;
    private String goods;

}
