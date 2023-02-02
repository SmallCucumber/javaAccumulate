package com.zmm.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("data_source")
public class DataSourceDTO {

    // 连接池名称
    @TableField(value = "POOL_NAME")
    private String poolName;
    // JDBC driver org.h2.Driver
    @TableField(value = "DRIVER_CLASS_NAME")
    private String driverClassName;
    // JDBC url 地址
    @TableField(value = "URL")
    private String url;
    // JDBC 用户名
    @TableField(value = "USER_NAME")
    private String username;
    // JDBC 密码
    @TableField(value = "PASS_WORD")
    private String password;
}
