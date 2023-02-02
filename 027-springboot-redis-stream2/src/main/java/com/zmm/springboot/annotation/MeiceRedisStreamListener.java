package com.zmm.springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RUNTIME)
public @interface MeiceRedisStreamListener {
	String streamKey() default "";

	String consumerGroup() default "";

	String consumerName() default "";
}
