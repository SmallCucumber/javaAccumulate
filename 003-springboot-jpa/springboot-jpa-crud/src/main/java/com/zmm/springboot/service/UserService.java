package com.zmm.springboot.service;

import com.zmm.springboot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getUserList();

    public User findUserById(long id);

    public void save(User user);

    public void edit(User user);

    public void delete(long id);


}
