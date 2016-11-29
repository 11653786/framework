package com.yt.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by PUZE84 on 2016/11/16.
 */
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Fields {

    /**
     * 是否必须
     *
     * @return
     */
    public boolean isMust() default true;

    public String desc() default "";

}
