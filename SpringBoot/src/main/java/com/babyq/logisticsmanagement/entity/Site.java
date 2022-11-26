package com.babyq.logisticsmanagement.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@TableName(value="site")//mabatis+默认实体类名和表名一致，若不一致需要注解指定
public class Site {
    //实体类的变量名要和数据库中的变量名一致，否则无法读取。顺序无所谓
    //注意使用驼峰命名
    @TableId(value = "site_id")
    private String siteId;
    private String siteRegion;
    private String siteName;
    private String sitePrincipleName;
    private String sitePrinciplePhone;
    private String siteUserId;
}
