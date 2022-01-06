package com.zmm.springboot.service.impl;

import com.mysql.cj.util.StringUtils;
import com.zmm.springboot.entity.UserDetail;
import com.zmm.springboot.param.UserDetailParam;
import com.zmm.springboot.repository.UserDetailRepository;
import com.zmm.springboot.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable){

        return null;

    }
}
