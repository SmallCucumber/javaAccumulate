package com.zmm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {//密码加密
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .requestMatchers()//系统中所有请求
                .antMatchers("/**")//SpringSecurity接管的请求/**系统所有请求
                .and()
                .authorizeRequests()//得到SpringSecurity接管的请求
                .antMatchers("/test/*")//给接管的请求(/**)中的/test/*
                .hasAnyAuthority("p3")//配置需要p1权限
                .antMatchers("/mbb/*")//给接管的请求(/**)中的/mbb/*
                .permitAll()//放行，无需权限
                .anyRequest()//其他请求
                .authenticated()//都需要认证
                .and()
                .csrf().disable()//关闭csrf
        ;
    }
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                .requestMatchers()//系统中所有请求
                .antMatchers("/**")//SpringSecurity接管的请求/**系统所有请求
                .and()
                .authorizeRequests()//得到SpringSecurity接管的请求
                .antMatchers("/test/*")//给接管的请求(/**)中的/test/*
                .hasAnyAuthority("p3")//配置需要p1权限
                .antMatchers("/mbb/*","/oauth/**","/login**")//给接管的请求(/**)中的/mbb/*
                .permitAll()//放行，无需权限
                .anyRequest()//其他请求
                .authenticated()//都需要认证
                .and()
                .csrf().disable()//关闭csrf
        ;
    }*/


    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("123456"))
                .roles("USER","ADMIN")
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("p1,p2"));
        //这里配置全局用户信息
    }
}
