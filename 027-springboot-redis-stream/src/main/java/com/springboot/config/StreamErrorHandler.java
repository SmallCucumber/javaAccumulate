package com.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ErrorHandler;

/**
 * @author ：zz
 * @date ：Created in 2022/5/19 10:24
 * @description：异常处理
 */
@Slf4j
public class StreamErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.error("stream异常：{}", t);
    }
}
