package com.zmm.springboot.repository.secondary;

import com.zmm.springboot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<User, String> {
}
