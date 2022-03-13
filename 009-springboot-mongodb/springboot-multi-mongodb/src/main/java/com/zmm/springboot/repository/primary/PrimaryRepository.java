package com.zmm.springboot.repository.primary;

import com.zmm.springboot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimaryRepository extends MongoRepository<User, String> {
}
