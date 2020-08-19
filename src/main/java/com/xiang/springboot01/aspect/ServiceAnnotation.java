package com.xiang.springboot01.aspect;

import java.lang.annotation.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ServiceAnnotation.java
 * @Description TODO
 * @createTime 2020年08月17日 16:52:00
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceAnnotation {
    String value() default "xxx";
}
