package com.zmm.springboot.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zmm.springboot.enums.UserSexEnum;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@TableName(value = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id",type = IdType.AUTO)//指定自增策略
	private Long id;

	@TableField(value = "userName")
	private String userName;

	@TableField(value = "passWord")
	private String passWord;

	@TableField(value = "user_sex")
	private UserSexEnum userSex;

	@TableField(value = "nick_name")
	private String nickName;


	public User(String userName, String passWord, UserSexEnum userSex) {
		this.userName=userName;
		this.passWord=passWord;
		this.userSex=userSex;
	}
}