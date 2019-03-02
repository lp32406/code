package com.eb.dianlianbao_server.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class GetPhoneCode
{
	public static String phoneTestCode()
	{
		String code = String.valueOf((int)((Math.random()*9+1)*100000));
		return code;
	}

	public static String requestCode()
	{
		String code = String.valueOf((int)((Math.random()*9+1)*1000));
		return code;
	}

	public static String getMD5(String str) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(str.getBytes());
            String md5=new BigInteger(1, md.digest()).toString(16);
            return fillMD5(md5);
        } catch (Exception e) {
			throw new RuntimeException("MD5:" + e.getMessage(), e);
        }
    }

    public static String fillMD5(String md5)
    {
        return (md5.length()==32?md5:fillMD5("0"+md5)).toUpperCase();
    }
}
