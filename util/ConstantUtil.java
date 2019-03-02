package com.eb.dianlianbao_server.util;

import org.springframework.stereotype.Component;

@Component
public class ConstantUtil {
	/**
	 * 微信�?发平台应用ID
	 */
	public static final String APP_ID = "wxe6f250d48a2970da";
	/**
	 * 应用对应的凭�?
	 */
	public static final String APP_SECRET="76432064eabd1579d029eb621529ee8e";///2131232123
	/**
	 * 微信支付商户�?
	 */
	public static final String MCH_ID="1507046511";
	/**
	 * 商户号对应的密钥
	 */
	public static final String PARTNER_key="IUEYswkdi5987dmkcbrAK465AP3848xd";
	/**
	 * 商品描述
	 */
	public static final String BODY="购买支付";

	/**
	 * 商户id
	 */
	//    public static final String PARTNER_ID="14698sdfs402dsfdew402";
	/**
	 * 常量固定�?
	 */
	public static final String GRANT_TYPE="client_credential";
	/**
	 * 获取预支付id的接口url
	 */
	public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 微信服务器回调�?�知url
	 */
	public static String NOTIFY_URL="http://xgx.ebenny.cn/yallavan_server/pay/app/tenpay/notify";
}