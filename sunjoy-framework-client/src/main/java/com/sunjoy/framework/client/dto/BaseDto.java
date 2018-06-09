package com.sunjoy.framework.client.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseDto implements Serializable{
	
	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = 2999128012607394035L;
	
	private Map<String, Object> attributes = new HashMap<String, Object>();

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public void putAttribute(String key,Object value) {
		this.attributes.put(key, value);
	}

}
