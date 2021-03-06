package com.zmm.springcloud.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();     //关闭跨域
        http.authorizeRequests()   //认证请求
                .anyRequest()      //对任何请求
                .authenticated()   //都需要认证
                .and()
                .httpBasic();      //使用Spring Security提供的登录界面
    }
}
