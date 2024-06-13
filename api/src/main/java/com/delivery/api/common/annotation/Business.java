package com.delivery.api.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Service
public @interface Business {
    /**
     * 비즈니스 로직을 처리해야 하는 부분에 달아주는 어노테이션
     */

    @AliasFor(annotation = Service.class)
    String value() default "";

}
