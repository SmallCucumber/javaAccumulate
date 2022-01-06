package com.zmm.springboot.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class BlogProperties {
	
	@Value("${zmm.blog.name}")
	private String name;
	
	@Value("${zmm.blog.title}")
	private String title;

}
