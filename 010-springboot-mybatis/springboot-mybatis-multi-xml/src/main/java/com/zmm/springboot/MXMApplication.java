package com.zmm.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zmm.springboot.com.zmm.mapper")
public class MXMApplication {

	public static void main(String[] args) {
		SpringApplication.run(MXMApplication.class, args);
	}
}
