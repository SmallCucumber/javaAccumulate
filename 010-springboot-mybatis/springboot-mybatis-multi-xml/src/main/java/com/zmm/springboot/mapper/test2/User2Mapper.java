package com.zmm.springboot.mapper.test2;

import com.zmm.springboot.model.User;

import java.util.List;

public interface User2Mapper {
	
	List<User> getAll();
	
	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

}