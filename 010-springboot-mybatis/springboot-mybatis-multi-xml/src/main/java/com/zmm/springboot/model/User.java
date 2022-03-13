package com.zmm.springboot.model;


import com.zmm.springboot.enums.UserSexEnum;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String passWord;
	private UserSexEnum userSex;
	private String nickName;


	public User(String userName, String passWord, UserSexEnum userSex) {
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
	}


}