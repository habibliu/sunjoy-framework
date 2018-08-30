package com.sunjoy.framework.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
/**
 * 微信验签注解
 * @author liuganchao<740033486@qq.com>
 * @date 2018年8月30日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WXValidSignature {
	
}
