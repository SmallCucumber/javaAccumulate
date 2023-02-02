package com.zmm.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {

    @TableId(type = IdType.AUTO)
    @TableField("ID")
    private Integer id;

    @TableField("NAME")
    private String name;

    @TableField("AGE")
    private Integer age;


    @TableField(exist = false)
    private String tenantName;
}
