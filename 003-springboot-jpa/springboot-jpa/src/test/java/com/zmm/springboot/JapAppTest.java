package com.zmm.springboot;


import com.zmm.springboot.entity.User;
import com.zmm.springboot.entity.UserDetail;
import com.zmm.springboot.repository.UserRepository;
import com.zmm.springboot.service.UserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JapAppTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void JapTest() {
        User user=User.builder()
                .id(1L)
                .email("email")
                .nickName("nickName")
                .passWord("passWord")
                .regTime("regTime")
                .userName("userName")
                .build();

        userRepository.save(user);

    }
}
