package com.babyq.logisticsmanagement.controller.dto;

import lombok.Data;

/*
* 接受前端登录请求参数
* */
@Data
public class UserDTO {
    private String username;
    private String password;
}
