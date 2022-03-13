package com.zmm.springboot;

import com.zmm.springboot.model.User;
import com.zmm.springboot.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser(){
        User user=User.builder()
                .id(1L)
                .passWord("123")
                .userName("234")
                .build();

        userRepository.saveUser(user);
    }
}
