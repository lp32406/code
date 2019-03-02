package com.eb.dianlianbao_server.util.alipay;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;


public class WechetUtils {
	/**
	 * 验证回调签名
	 * 
	 * @return
	 */
	public static boolean isTenpaySign(Map<String, String> map,String WX_PARTNER_KEY) {
		String characterEncoding = "utf-8";
		String charset = "utf-8";
		String signFromAPIResponse = map.get("sign");
		if (signFromAPIResponse == null || signFromAPIResponse.equals("")) {
			return false;
		}
		// 过滤空 设置 TreeMap
		SortedMap<String, String> packageParams = new TreeMap<String, String>();

		for (String parameter : map.keySet()) {
			String parameterValue = map.get(parameter);
			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = packageParams.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();

		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String k = entry.getKey();
			String v = entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + WX_PARTNER_KEY);
		// 将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
		// 算出签名
		String resultSign = "";
		String tobesign = sb.toString();

		if (null == charset || "".equals(charset)) {
			resultSign = Md5Util.MD5Encode(tobesign, characterEncoding).toUpperCase();
		} else {
			try {
				resultSign = Md5Util.MD5Encode(tobesign, characterEncoding).toUpperCase();
			} catch (Exception e) {
				resultSign = Md5Util.MD5Encode(tobesign, characterEncoding).toUpperCase();
			}
		}

		String tenpaySign = packageParams.get("sign").toUpperCase();
		return tenpaySign.equals(resultSign);
	}
}
