package com.zmm.springboot;

import com.zmm.springboot.rabbit.delay.DelayProd;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DelayTest {

	@Autowired
	private DelayProd delayProd;

	@Test
	public void hello() throws Exception {
		delayProd.sendDelay();
	}


}