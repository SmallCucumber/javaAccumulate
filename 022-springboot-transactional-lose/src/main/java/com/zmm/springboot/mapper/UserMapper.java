package com.zmm.springboot.mapper;

import com.zmm.springboot.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insert(User user);

}
