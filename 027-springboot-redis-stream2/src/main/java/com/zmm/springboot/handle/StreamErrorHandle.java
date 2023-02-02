package com.zmm.springboot.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ErrorHandler;

@Slf4j
public class StreamErrorHandle implements ErrorHandler {
	@Override
	public void handleError(Throwable throwable) {
		throwable.printStackTrace();
		log.error("redis error:", throwable);
	}
}
