package com.flouis.interceptor.demo.base;

public enum ResultEnum {

	SUCCESS(20000, "请求成功"),
	FAIL(40000, "服务器异常，请求失败！");

	private int code;
	private String description;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	ResultEnum(int code, String description){
		this.setCode(code);
		this.setDescription(description);
	}

}
