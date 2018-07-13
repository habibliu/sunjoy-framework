package com.sunjoy.framework.client.dto;

import java.io.Serializable;

import com.sunjoy.framework.constants.CommonConstant;


/**
 * 框架统一返回对象，
 * @author liuganchao
 *
 */
public class Response implements Serializable {

	/**
	 * @serialField serialVersionUID
	 */
	private static final long serialVersionUID = 6661646855012552568L;
	private String code;
	private Object data;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", data=" + data + ", message=" + message + "]";
	}

	public Response(String code, Object data, String message) {
		super();
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public Response() {
		super();
		this.code=CommonConstant.SUCCESS_CODE;
	}
}
