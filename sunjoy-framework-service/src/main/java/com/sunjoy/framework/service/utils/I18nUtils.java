package com.sunjoy.framework.service.utils;

import java.util.ArrayList;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class I18nUtils {
	@Resource
	private MessageSource messageSource;

	public String getMessage(String key) {
		return this.getMessage(key, (String[]) null);
	}

	public String getMessage(String key, String[] args) {
		return this.getMessage(key, args, (HttpServletRequest) null);
	}

	public String getMessage(String key, String[] args, HttpServletRequest request) {
		return this.getMessage(key, args, (String) null, request);
	}

	public String getMessage(String key, String[] args, String defaultMessage, HttpServletRequest request) {
		Locale locale = null;
		if (null == request) {
			locale = Locale.getDefault();
		} else {
			locale = request.getLocale();
		}

		return this.getMessageByLocale(key, args, defaultMessage, locale);
	}

	public String getMessageByLocale(String key, String[] args, Locale locale) {
		return this.getMessageByLocale(key, args, (String) null, locale);
	}

	public String getMessageByLocale(String key, String[] args, String defaultMessage, Locale locale) {
		if (null == locale) {
			locale = Locale.getDefault();
		}

		return null == defaultMessage ? this.messageSource.getMessage(key, this.getArgsMessage(args, locale), locale)
				: this.messageSource.getMessage(key, this.getArgsMessage(args, locale),
						this.messageSource.getMessage(defaultMessage, (Object[]) null, locale), locale);
	}

	private Object[] getArgsMessage(String[] args, Locale locale) {
		if (null != args && args.length != 0) {
			ArrayList<String> msgs = new ArrayList<String>();
			String[] arg3 = args;
			int arg4 = args.length;

			for (int arg5 = 0; arg5 < arg4; ++arg5) {
				String arg = arg3[arg5];
				if (null != arg) {
					msgs.add(this.messageSource.getMessage(arg, (Object[]) null, locale));
				}
			}

			return msgs.toArray();
		} else {
			return args;
		}
	}
}
