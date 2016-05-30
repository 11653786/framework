package com.yt.util.yt.annotation.system;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不需要session
 * @author zhangsan
 * @version 1.0
 * @package com.yt.util.yt.annotation.system
 * @date 2016/4/22 0022 9:53
 * @descption: 疯狂的王麻子团队!
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BmsUnSession {
    String value() default "";
}
