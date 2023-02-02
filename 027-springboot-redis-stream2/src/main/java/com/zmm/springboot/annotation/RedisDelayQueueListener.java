package com.zmm.springboot.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 延迟队列
 * Copyright © 2020 meicet. All rights reserved.
 *
 * @author zyx
 * @date 2020-10-21 10:48:50
 */
@Target({METHOD})
@Retention(RUNTIME)
public @interface RedisDelayQueueListener {

	String queue() default "queue";

}
