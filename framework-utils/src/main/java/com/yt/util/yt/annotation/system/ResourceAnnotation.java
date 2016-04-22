package com.yt.util.yt.annotation.system;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceAnnotation {

	String value() default "";

	/**
	 * 资源名称
	 */
	String name() default "";

	/**
	 * 上级资源名称
	 */
	String pName() default "";

	/**
	 * 资源的类型，默认是菜单，
	 */
	String resourceType() default "";

	/**
	 * 资源组名称，默认是系统管理
	 */
	String resourceGroup() default "";

	String url() default "";

	String remark() default "";

	/**
	 * 上级资源是否是根资源
	 * 
	 */
	boolean parentIsRoot() default false;

}
