package com.sunjoy.framework.exception;

import org.apache.commons.lang3.StringUtils;

public class CommonException extends RuntimeException {

	/**
	 * @serialField serialVersionUID
	 */
	private static final long serialVersionUID = -4925340663591352936L;
	private String code;
	private String message;
	private Throwable throwable;
	private transient Object[] values;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getThrowable() {
		return this.throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public Object[] getValues() {
		return this.values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public CommonException(String code, String message, Object[] values, Throwable cause) {
		this(code, message, cause);
		this.values = values;
	}

	private CommonException(String code, String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.code = code;
	}

	public CommonException(String code) {
		this(code, (String) null, (Object[]) null, (Throwable) null);
	}

	public CommonException(String code, String message) {
		this(code, message, (Object[]) null, (Throwable) null);
	}

	public CommonException(String code, String message, Object[] values) {
		this(code, message, values, (Throwable) null);
	}

	public String getErrorCode() {
		String errorCode = "00099";
		String message = this.getMessage() == null ? this.getCode() : this.getMessage();
		if (!StringUtils.isEmpty(message)) {
			String[] messageValues = StringUtils.split(message, ":");
			if (messageValues.length > 1) {
				errorCode = messageValues[0];
			}
		}

		return errorCode;
	}
}
