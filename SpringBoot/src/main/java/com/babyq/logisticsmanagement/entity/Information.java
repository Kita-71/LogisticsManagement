package com.babyq.logisticsmanagement.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="information")//mabatis+默认实体类名和表名一致，若不一致需要注解指定
public class Information {
    //实体类的变量名要和数据库中的变量名一致，否则无法读取。顺序无所谓
    //注意使用驼峰命名
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String description;
}
