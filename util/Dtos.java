package com.eb.dianlianbao_server.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * Dtos
 *
 * @author Administrator
 * @date 2018年9月28日 下午4:07:45
 */
public class Dtos extends HashMap<String, Object> {

	/**
	 *
	 */
	private static final long serialVersionUID = 582435297694341010L;

	/**
	 * 获取Dtos实例
	 *
	 * @param request
	 * @return Dtos
	 */
	public static Dtos newDto(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		Dtos dto = new Dtos();
		for (Entry<String, String[]> entry : map.entrySet()) {
			dto.put(entry.getKey(), String.join(",", entry.getValue()));
		}
		return dto;
	}

	public static Dtos newObjectMap(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		Dtos returnMap = new Dtos();
		for (Entry<String, String[]> entry : map.entrySet()) {
			Object objValue = entry.getValue();
			String name = entry.getKey();
			if (objValue == null) {
				returnMap.put(name, "");
			} else if (objValue instanceof String[]) {
				returnMap.put(name, String.join(",", entry.getValue()));
			} else {
				returnMap.put(name, objValue);
			}
		}
		return returnMap;
	};

	/**
	 * 获取当前用户id
	 *
	 * @param request
	 * @return Integer
	 */
//	public static Integer getCurrentUserId(HttpServletRequest request) {
//		TbUserAdmin object = (TbUserAdmin) request.getSession().getAttribute(Constants.ADMIN_USER);
//		return object == null ? null : Integer.valueOf(object.getAdminId());
//	}

}
