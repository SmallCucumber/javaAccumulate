package com.zmm.springboot.service;

import com.zmm.springboot.mapper.UserMapper;
import com.zmm.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    @Autowired
    private UserMapper userMapper;

    public void exception(User user){

        userMapper.insert(user);
    }
}
