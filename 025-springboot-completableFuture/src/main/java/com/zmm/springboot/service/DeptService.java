package com.zmm.springboot.service;

import com.zmm.springboot.model.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeptService {

    public Dept getById(Integer id) {
        log.info("线程：" + Thread.currentThread().getName() + " getById(" + id + ")");
        if (id == 1){
            sleep();

            return new Dept(1, "研发一部");
        } else if (id == 2){
            return new Dept(2, "研发二部");
        } else {
            throw null;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }
    }
}
