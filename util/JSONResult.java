package com.eb.dianlianbao_server.util;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;
import com.eb.dianlianbao_server.pojo.Page;

public class JSONResult extends HashMap<String, Object> {

	private static final long serialVersionUID = 406463182042445636L;

	private static JSONResult json;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String DATA = "data";
	private static final String PAGE = "page";

	/**
	 * 成功时返回
	 *
	 * @return JSONResult
	 */
	public static JSONResult success() {
		json = new JSONResult();
		json.put(SUCCESS, SUCCESS);
		json.put(DATA, DATA);
		return json;
	}

	/**
	 * 失败时返回
	 *
	 * @return JSONResult
	 */
	public static JSONResult fail() {
		json = new JSONResult();
		json.put(FAIL, FAIL);
		json.put(DATA, DATA);
		return json;
	}

	/**
	 * 设置响应数据
	 *
	 * @param data
	 * @return JSONResult
	 */
	public JSONResult setData(Object data) {
		json.put(DATA, data);
		return json;
	}

	/**
	 * 拓展数据
	 *
	 * @param value
	 * @return JSONResult
	 */
	public JSONResult setExt(String key, Object value) {
		json.put(key, value);
		return json;
	}

	/**
	 * 设置分页信息
	 *
	 * @param page
	 * @return JSONResult
	 */
	public JSONResult setPage(Page page) {
		json.put(PAGE, page);
		return json;
	}

	/**
	 * 设置总条数
	 * <p></p>
	 * @param count
	 * @return
	 */
	public JSONResult setCount(int count) {
		json.put("count", count);
		return json;
	}

	/**
	 * 返回结果信息
	 *
	 * @param code
	 * @param message
	 * @return JSONResult
	 */
	public static JSONResult result(Integer code, String message) {
		json = new JSONResult();
		json.put("code", code);
		json.put("message", message);
		json.put("data", new JSONObject());
		return json;
	}

	/**
	 * 返回结果信息
	 * @param code
	 * @param message
	 * @param obj
	 * @return JSONResult
	 */
	public static JSONResult result(Integer code, String message, Object obj) {
		json = new JSONResult();
		json.put("code", code);
		json.put("message", message);
		if (obj == null) {
			json.put("data", new JSONObject());
		} else {
			json.setData(obj);
		}
		return json;
	}
	
	/**
	 * 返回结果信息
	 * @param code
	 * @param message
	 * @param obj
	 * @return JSONResult
	 */
	public static JSONResult result2(Integer code, String message, Object obj,int totalPage,int totalNum) {
		json = new JSONResult();
		json.put("code", code);
		json.put("totalPage", totalPage);
		json.put("totalNum", totalNum);
		json.put("message", message);
		if (obj == null) {
			json.put("data", new JSONObject());
		} else {
			json.setData(obj);
		}
		return json;
	}
}
