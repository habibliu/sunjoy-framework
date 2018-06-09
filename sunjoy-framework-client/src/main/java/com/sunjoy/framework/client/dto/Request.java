package com.sunjoy.framework.client.dto;

import javax.validation.Valid;

public class Request<T extends BaseDto> {

	@Valid
	private Header header;

	@Valid
	private T data;

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Request [header=" + header + ", data=" + data + "]";
	}

}
