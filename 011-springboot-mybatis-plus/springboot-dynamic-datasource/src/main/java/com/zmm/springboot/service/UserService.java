package com.zmm.springboot.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmm.springboot.entity.User;
import com.zmm.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(()->"");

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @DSTransactional
    public void add(){
        User user = new User();
        user.setName("数据库1");
        // 设置租户可以通过spel表达式来获取 #user.tenantName
        user.setTenantName("tenant1");
        // 设置当前线程的数据源名称
        threadLocal.set("tenant1");
        userService.addUser(user);
        User user2 = new User();
        user2.setName("数据库2");
        user2.setTenantName("tenant2");
        threadLocal.set("tenant2");
        userService.addUser(user2);
        User user3 = new User();
        user3.setName("数据库3");
        user3.setTenantName("tenant3");
        threadLocal.set("tenant3");
        userService.addUser(user3);
        // int a=1/0; // 测试多源数据库事务回滚
    }

    @DS("#test!=null?'':T(com.zmm.springboot.service.UserService).threadLocal.get()")
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @DS("#header.tenant")
    public void addHeader() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String tenant=request.getHeader("tenant");
        User user = new User();
        user.setName(tenant);

        userMapper.insert(user);
    }

}
