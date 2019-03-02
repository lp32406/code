package com.eb.dianlianbao_server.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;


/**
 * 对象转换
 * @author Administrator
 * @date 2018年12月13日 下午6:26:45
 */
public class BeanConvert {

	/**
	 * 转换成指定对象
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T convert(Map<String, Object> map, Class<T> clazz) {
		T t = null;
		try {
			t = clazz.newInstance();
			BeanUtils.populate(t, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			LogUtils.logError("对象转换错误", e);
			return null;
		} catch (InstantiationException e) {
			LogUtils.logError("对象实例化失败", e);
			return null;
		}
		return t;
	}

	/**
	 * 对象转Map
	 * @param obj
	 * @return Map<String, Object>
	 */
	public static Map<String, String> beanToMap(Object obj) {
		Map<String, String> mapbean = new HashMap<>();
		try {
			mapbean = BeanUtils.describe(obj);
		} catch (IllegalAccessException e) {
			LogUtils.logError("map转换异常", e);
		} catch (InvocationTargetException e) {
			LogUtils.logError("map转换异常", e);
		} catch (NoSuchMethodException e) {
			LogUtils.logError("map转换异常", e);
		}
		return mapbean;
	}

}
