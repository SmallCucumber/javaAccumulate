package com.zmm.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zmm.springboot.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
