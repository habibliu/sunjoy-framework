package com.sunjoy.framework.service.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.sunjoy.framework.client.dto.Response;

/**
 * 信息处理类
 * @author liuganchao
 *
 */
public class MessageUtils {
	/**
	 * 静态类，需要私有化构造函数
	 */
	private MessageUtils() {
	}

	private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);

	public static Response getResponseByMessage(String message) {
		Response result = null;
		String responseCode = "";
		String responseMessage = message;

		String[] messageValues = StringUtils.split(message, ":");
		if (messageValues.length > 1) {
			responseCode = messageValues[0];
			responseMessage = messageValues[1];
		} else {
			logger.warn(String.format("The message code is not set in %s", message));
		}

		result = new Response(responseCode, "", responseMessage);
		return result;
	}

	public static Response getResponseByMessageKey(String msgKey, String defaultMessage, HttpServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		WebApplicationContext context = (WebApplicationContext) servletContext
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		String message = context.getMessage(msgKey, null, defaultMessage, request.getLocale());

		if (message != null && message.equals(msgKey)) {
			logger.warn(String.format("The message key : %s is not set!", msgKey));
		}
		return getResponseByMessage(message);
	}
}
