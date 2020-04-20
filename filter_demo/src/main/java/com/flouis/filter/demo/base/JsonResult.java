package com.flouis.filter.demo.base;

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
	private String msg;
	private Object data;

	public static JsonResult success(){
		JsonResult result = new JsonResult();
		result.setFlag(true);
		result.setMsg("请求成功");
		return result;
	}

	public static JsonResult success(String msg){
		return new JsonResult(true, msg, null);
	}

	public static JsonResult success(Object data){
		return new JsonResult(true, "请求成功", data);
	}

	public static JsonResult success(String msg, Object data){
		return new JsonResult(true, msg, data);
	}

	public static JsonResult fail(){
		return new JsonResult(false, "服务器异常，请求失败！", null);
	}

	public static JsonResult fail(String msg){
		return new JsonResult(false, msg, null);
	}

}
