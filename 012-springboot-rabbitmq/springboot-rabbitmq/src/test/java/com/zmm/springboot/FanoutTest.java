package com.zmm.springboot;

import com.zmm.springboot.rabbit.fanout.FanoutSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutTest {

	@Autowired
	private FanoutSender sender;

	@Test
	public void fanoutSender() throws Exception {
		sender.send();
	}

	@Test
	public void fanoutSender2() throws Exception {
		sender.send2();
	}
}