package com.zmm.springboot.service;

import com.zmm.springboot.entity.UserDetail;
import com.zmm.springboot.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailService {

    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable);

}
