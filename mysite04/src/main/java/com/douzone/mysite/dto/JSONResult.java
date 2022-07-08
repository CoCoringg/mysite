package com.douzone.mysite.dto;

public class JSONResult {
	private String result;	/* success or fail */
	private Object data;	/* if success, set */
	private String Message; /* if fail, set */

	private JSONResult(Object data) {
		this.result = "success";
		this.data = data;
	}	
	
	private JSONResult(String message) {
		this.result = "fail";
		this.data = null;
		this.Message = message;
	}	
	
	public static JSONResult success(Object data) {
		return new JSONResult(data);
	}
	
	public static JSONResult fail(String message) {
		return new JSONResult(message);
	}

	public String getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}

	public String getMessage() {
		return Message;
	}
	
	
	
}
