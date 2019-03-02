package com.eb.dianlianbao_server.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public MD5() {
	}

	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		// System.out.println("iRet="+iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	public static String byteToNum(byte bByte) {
		int iRet = bByte;
		System.out.println("iRet1=" + iRet);
		if (iRet < 0) {
			iRet += 256;
		}
		return String.valueOf(iRet);
	}

	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String GetMD5Code(String strObj) {
		String resultString = null;
		try {
			resultString = new String(strObj);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteToString(md.digest(strObj.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		System.out.println("加密结果" + resultString.toUpperCase());
		return resultString;
	}

	 /**
     * md5解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    public static String Code(){
    	String randomcode = "";
    	// 用字符数组的方式随机
    	String model = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	char[] m = model.toCharArray();
    	for (int j = 0; j < 10; j++) {
    		char c = m[(int) (Math.random() * 36)];
    		// 保证六位随机数之间没有重复的
    		if (randomcode.contains(String.valueOf(c))) {
    			j--;
    			continue;
    			}
    		randomcode = randomcode + c;
    		}
    	return randomcode;
    }
}
