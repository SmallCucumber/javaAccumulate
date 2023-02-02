package com.zmm.springboot.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright ©  meicet. All rights reserved.
 *
 * @author zyx
 * @date 2021-06-18 15:03
 */
@Data
public class MeiceUser implements Serializable {

	private static final long serialVersionUID = 8609844882953625196L;
	private String name;

	private Integer age;

	private Long date;
}
