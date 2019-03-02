package com.eb.dianlianbao_server.util;

import java.util.Random;

import com.eb.dianlianbao_server.util.alipay.Md5Util;



public class WXUtil {
    /**
     * 生成随机字符串
     * @return
     */
    public static String getNonceStr() {
        Random random = new Random();
        return Md5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "utf8");
    }
    /**
     * 获取时间戳
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
