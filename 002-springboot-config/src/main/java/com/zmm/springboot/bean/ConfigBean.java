package com.zmm.springboot.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix="zmm.blog")
public class ConfigBean {
	private String name;
	private String title;
	private String wholeTitle;

}
