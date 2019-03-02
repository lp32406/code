package com.eb.dianlianbao_server.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.util.StringUtils;


public class StringTools {

	/**
	 * 批量判空
	 */
	public static boolean isNotEmptyBatch(String... strs) {
		for (String str : strs) {
			if (StringUtils.isEmpty(str))
				return false;
		}
		return true;
	}
	
	/**
	 * 获得随机的字符串(待整�??)
	 */
	public static String getRandomString() {
		return String.valueOf(new Date().getTime()/1024);
	}
	
	/**
	 * 批量转换对象为String类型
	 */
	public static String[] toStringBatch(Object... objs) {
		ArrayList<String> list = new ArrayList<>();
		for (Object obj : objs) {
			String str = String.valueOf(obj);
			list.add(str);
		}
		String[] array = list.toArray(new String [list.size()]);
		return array;
	}
	
	public static String getMD5() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
           
            md.update(getRandomString().getBytes());
            String md5=new BigInteger(1, md.digest()).toString(16);
            return fillMD5(md5);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密发生异常:"+e.getMessage(),e);
        }
    }
	
	public static String fillMD5(String md5)
    {
        return (md5.length()==32?md5:fillMD5("0"+md5)).toUpperCase();
    }

	/**
	 * 如果全部参数为空返回false，包含有�?个不为空返回true
	 * @param content
	 * @return
	 */
	public static boolean isALLEmptyBatch(String ...content) {
		// TODO Auto-generated method stub
		int length = content.length;
		for (String str: content) {
			if(StringUtils.isEmpty(str)){
				length+=-1;
			}else{
				return true;
			}
		}
		if(length==0){
			return false;
		}
		return true;
	}
	
	/**
	 * 接受字符串Ojbect类型为空返回true
	 * @return
	 */
	public static boolean judgeTextEmpty(Object... str) {
		// TODO Auto-generated method stub
		for (Object obj : str) {
			if(obj==null)
				return true;
			if(String.valueOf(obj).trim().equals("")){
				return true;
			}
		}
		return false;
	}
}
