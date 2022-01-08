package com.zmm.springboot;

import com.zmm.springboot.entity.User;
import com.zmm.springboot.repository.test2.UserTest2Repository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaAppTest {

    @Autowired
    private UserTest2Repository userTest2Repository;

    @Test
    public void JpaApp(){
        User user=User.builder()
                .id(1L)
                .email("email")
                .nickName("nickName")
                .passWord("passWord")
                .regTime("regTime")
                .userName("userName")
                .build();
        userTest2Repository.save(user);
    }
}
