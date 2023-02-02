package com.zmm.springboot.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({METHOD})
@Retention(RUNTIME)
public @interface RedisListener {

    String queue() default "queue";

    long timeout() default 30;

}
