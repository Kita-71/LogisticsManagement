package com.babyq.logisticsmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Order {
    //实体类的变量名要和数据库中的变量名一致，否则无法读取。顺序无所谓
    //注意使用驼峰命名
    @TableId(value = "id")
    private String orderId;
    private String origin;
    private String dest;
    private String goods;

}
