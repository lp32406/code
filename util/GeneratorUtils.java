package com.eb.dianlianbao_server.util;

import java.util.Random;

/**
 * 随机码生成工具类
 * @author Administrator
 * @date 2018年11月22日 下午6:20:53
 */
public class GeneratorUtils {

	private static final String[] storeInvitationChars = { "a", "c", "b", "d", "f", "e", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2",
			"3", "4", "5", "6", "7", "8", "9" };

	/**
	 * 生成邀请码
	 * <p>生成随机的数字+字母组合</p>
	 * @return String
	 */
	public static String generatCode() {
		String randomStr = "";
		Random random = null;
		for (int i = 0; i < 11; i++) {
			random = new Random();
			int randomIndex = random.nextInt(35);
			randomStr = randomStr + storeInvitationChars[randomIndex];
		}
		return randomStr.toUpperCase();
	}

}
