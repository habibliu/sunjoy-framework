package com.sunjoy.framework.anotation.processor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 * @author liuganchao<740033486@qq.com>
 * @date 2018年8月30日
 */
@Aspect
public class WXSignatrueVerify {
	/**
     * 对Controller进行安全和身份校验  
     */
    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(is)")
    public Object validIdentityAndSecure(ProceedingJoinPoint pjp, SecureValid is){
    	
    	return null;
    }
}
