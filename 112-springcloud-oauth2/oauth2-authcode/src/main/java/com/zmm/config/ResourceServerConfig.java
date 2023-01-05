package com.zmm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)//开启注解鉴权
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    //资源服id标识
    public static final String RESOURCE_ID = "res1";

    @Autowired
    TokenStore tokenStore;

    /*@Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)//资源 id
                .tokenStore(tokenStore)
                .stateless(true);
    }*/

    //配置资源服安全规则
   /* @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//授权的请求
                .anyRequest()//任何请求
                .authenticated()//需要身份认证
                .and().csrf().disable();
    }*/
}
