package com.babyq.logisticsmanagement.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@TableName(value="user")//mabatis+默认实体类名和表名一致，若不一致需要注解指定
public class User {

    @TableId(value = "id")
    private Integer userid;//若此使用注解指定id，postman指定的就应该是idhaha而不是id
    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
}
