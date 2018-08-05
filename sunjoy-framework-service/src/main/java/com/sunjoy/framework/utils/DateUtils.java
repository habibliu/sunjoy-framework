package com.sunjoy.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 格式化日期，默认以yyyy-MM-dd的格式显示
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format.format(date);
	}
}
