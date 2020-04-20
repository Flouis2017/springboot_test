package com.flouis.interceptor.demo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JsonResult {

	private Boolean flag;
	private Integer code;
	private String msg;
	private Object data;

	public static JsonResult success(){
		JsonResult result = new JsonResult();
		result.setFlag(true);
		result.setCode(ResultEnum.SUCCESS.getCode());
		result.setMsg(ResultEnum.SUCCESS.getDescription());
		return result;
	}

	public static JsonResult success(String msg){
		return new JsonResult(true, ResultEnum.SUCCESS.getCode(), msg, null);
	}

	public static JsonResult success(Object data){
		return new JsonResult(true, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDescription(), data);
	}

	public static JsonResult success(String msg, Object data){
		return new JsonResult(true, ResultEnum.SUCCESS.getCode(), msg, data);
	}

	public static JsonResult fail(){
		return new JsonResult(true, ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getDescription(), null);
	}

	public static JsonResult fail(String msg){
		return new JsonResult(true, ResultEnum.FAIL.getCode(), msg, null);
	}

}
