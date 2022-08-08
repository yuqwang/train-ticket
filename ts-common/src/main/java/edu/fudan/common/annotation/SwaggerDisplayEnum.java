package edu.fudan.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记注解，没有字段，仅是标记作用，
 * 标记到的枚举类才能在 swagger 文档中展示
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerDisplayEnum {
    String index() default "index";

    String name() default "name";
}