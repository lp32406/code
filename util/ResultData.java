package com.eb.dianlianbao_server.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class ResultData<T> {

	private int code;
	
	private String message;
	
	private T data;

	private ResultData(T data, String message) {
		this.data = data;
		this.message = message;
	}
	
	private ResultData(T data, String message, int code) {
		this.data = data;
		this.message = message;
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public static <T> ResultData<T> success(T data, String msg)
	{
		return new ResultData<T>(data, msg, 200);
	}
	
	public static <T> ResultData<JSONObject> success_obj(String msg)
	{
		return new ResultData<JSONObject>(new JSONObject(), msg, 200);
	}
	
	public static <T> ResultData<T> error(T data, String msg) {
		return new ResultData<T>(data, msg, 201);
	}
	public static <T> ResultData<JSONObject> error_obj(String msg) {
		return new ResultData<JSONObject>(new JSONObject(), msg, 201);
	}
	
	public static <T> ResultData<JSONArray> error_array(String msg) {
		return new ResultData<JSONArray>(new JSONArray(), msg, 201);
	}
	
	public static <T> ResultData<T> parametersMissError(T data, String msg) {
		return new ResultData<T>(data, msg, 402);
	}
	public static <T> ResultData<JSONObject> parametersMissError_obj(String msg) {
		return new ResultData<JSONObject>(new JSONObject(), msg, 402);
	}
	
	public static <T> ResultData<T> build(T data, String msg, int code) {
		return new ResultData<T>(data, msg, code);
	}

	
}
