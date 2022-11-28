package com.babyq.logisticsmanagement.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value="logistics_order")//mabatis+默认实体类名和表名一致，若不一致需要注解指定
public class Order {
    //实体类的变量名要和数据库中的变量名一致，否则无法读取。顺序无所谓
    //注意使用驼峰命名
    @Getter
    @AllArgsConstructor
    public enum PickupMethod {
        order_code(1, "code"), in_person(2, "in_person");
        @EnumValue
        private final int code;
        private String name;
    }
    @Getter
    @AllArgsConstructor
    public enum State {
        reserve(1, "reserve"),in_transport(2, "in_transport"),pending_pickup(3, "pending_pickup"),done(4, "done");
        @EnumValue
        private final int code;
        private String name;
    }

    @TableId(value = "orderId")
    private String orderId;
    private String goods;
    private String origin;
    private String senderName;
    private String senderPhone;
    private String dest;
    private String receiverName;
    private String receiverPhone;
    private  Integer currentSite;
    private PickupMethod pickupMethod;
    private LocalDateTime bookTime;
    private LocalDateTime sendTime;
    private LocalDateTime doneTime;
    private  State state;
    private String postscript;
    private Integer senderUid;
    private Integer receiverUid;
}
