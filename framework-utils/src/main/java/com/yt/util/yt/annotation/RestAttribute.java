package com.yt.util.yt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RestAttribute {

    /**
     * 鍚嶇О鎻忚堪
     *
     * @return
     * @author LiuYiJun
     * @date 2015骞�鏈�4鏃�
     */
    String name() default "";

    /**
     * 闀垮害
     *
     * @return
     * @author LiuYiJun
     * @date 2015骞�鏈�4鏃�
     */
    int len() default 0;


    /**
     * 闈炵┖
     *
     * @return
     * @author LiuYiJun
     * @date 2015骞�鏈�4鏃�
     */
    boolean notnull() default true;

    /**
     * 璇︾粏澶囨敞
     *
     * @return
     * @author LiuYiJun
     * @date 2015骞�鏈�4鏃�
     */
    String remark() default "";

    /**
     * 鏄惁鏄剧ず璇ュ睘鎬�
     *
     * @return
     * @author LiuYiJun
     * @date 2015骞�鏈�4鏃�
     */
    boolean showInfn() default true;

}