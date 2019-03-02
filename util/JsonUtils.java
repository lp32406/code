package com.eb.dianlianbao_server.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eb.dianlianbao_server.pojo.TbGoodsSpec;

public class JsonUtils {
	
	/**
	 * 获取json数组
	 * @param json
	 * @param key
	 * @return
	 */
	public static JSONArray jsonStrToArray(String json,String key){
		JSONArray jsonArray;
		try {
			JSONObject jso=JSON.parseObject(json);
			jsonArray = jso.getJSONArray(key);
			return jsonArray;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json转对象
	 * @param json
	 * @param t1
	 * @return
	 */
	public static <T> T toJavaObject(JSONObject json,Class<T> t1){
		try {
			return  (T) JSON.toJavaObject(json, t1);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json对象返回json字符串
	 * @param json
	 * @param t1
	 * @return
	 */
	public static String toJavaObjectStr(JSONObject json){
		try {
			return JSON.toJSONString(json);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * json字符串转json对象
	 * @param json
	 * @param t1
	 * @return
	 */
	public static JSONObject toJSONObject(String jsonStr){
		try {
			return JSON.parseObject(jsonStr);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject getJsonResult(int totalPage, String message, int code,Object data) {
		JSONObject fjson=new JSONObject();
		fjson.put("code", code);
		fjson.put("totalPage", totalPage);
		fjson.put("data",data);
		fjson.put("message", message);
		return fjson;
	}
}
