package com.eb.dianlianbao_server.util;

import java.util.HashMap;
import java.util.Map;



/**
 * 文字提示语说明
 * 
 * @author WEIMING
 *
 */
public final class MarkedCaption1 {
	// /**参数缺失*/
	// public static String parameter_lack="参数缺失";
	// /**签名缺失*/
	// public static String sign_lack="签名缺失";
	// /**请输入支付密码！*/
	// public static String PAY_PWD_EMPTY="请输入支付密码！";
	// /**支付密码错误！*/
	// public static String PAY_PWD_ERROR="支付密码错误！";
	// /**请先设置支付密码！*/
	// public static String PAY_PWD_NOT_SETTING="请先设置支付密码！";
	// /**Insufficient Balance！*/
	// public static String USER_BALANCE_NOT_ENOUGH="Insufficient Balance！";
	// /**签名错误*/
	// public static String sign_error="签名错误";
	// /**参数错误*/
	// public static String parameter_error="参数错误";
	// /**图片不能为空*/
	// public static String PARAMETER_IMG_LACK="图片不能为空";
	// /**该账号已冻结,请联系管理员*/
	// public static String user_freeze="该账号已冻结,请联系管理员";
	// /**账号或密码错误*/
	// public static String account_or_passwrod_error="账号或密码错误";
	// /**请登录后查看*/
	// public static String LOGOIN_OPERATOR="请登录后查看!";
	// /**请登录后进行操作*/
	// public static String LOGOIN_OPERATOR_OPERATOR="请登录后进行操作!";
	// /**登录成功*/
	// public static String login_success="登录成功";
	// /**电话号码为空*/
	// public static String phone_empty="电话号码为空";
	// /**用户不存在*/
	// public static String user_not_exsit="用户不存在";
	// /**用户不存在*/
	// public static String user_exsit="用户已存在";
	// /**密码为空*/
	// public static String password_empty="密码为空";
	// /**注册成功*/
	// public static String regist_success="注册成功";
	// /**注册失败*/
	// public static String regist_falied="注册失败";
	// /**修改登录密码成功*/
	// public static String update_login_pwd_success="修改登录密码成功";
	// /**修改登录密码失败*/
	// public static String update_login_pwd_failed="修改登录密码失败";
	// /**修改登录密码成功*/
	// public static String update_success="修改成功";
	// /**修改登录密码失败*/
	// public static String update_failed="修改失败";
	// /**get success*/
	// public static String get_success="get success";
	// /**"请输入6位数字密码!"*/
	// public static String input_six_pwd="请输入6位数字密码!";
	// /**""设置支付密码成功!""*/
	// public static String update_pwd_success="设置支付密码成功!";
	// /**""设置支付密码失败!""*/
	// public static String update_pwd_falied="设置支付密码失败!";
	// /**""日期转换异常!""*/
	// public static String date_tra_error="日期转换异常!";
	// /**"""请选择日期!""*/
	// public static String date_lack="请选择日期!";
	// /**"""JSON格式错误!""*/
	// public static String JSON_EORROR="JSON格式错误";
	// /**"""JSON参数缺失!""*/
	// public static String JSON_LACK="JSON参数缺失";
	// /**该活动不存在!*/
	// public static String ACTIVITY_NOT_EXSIT="该活动不存在";
	// /**该内容不存在!*/
	// public static String MESSAGE_NOT_EXSIT="该内容不存在";
	// /**不能选择指定日期的活动重新发布*/
	// public static String ACTIVITY_SELECT_DESTIN_DATE="不能选择指定日期的活动重新发布";
	// /**非发布人不能执行该操作*/
	// public static String ACTIVITY_NOT_BELONG_PUBLISHUSER="非发布人不能执行该操作";
	// /**您已经成功该活动的一员了*/
	// public static String ACTIVITY_USER_EXIST="您已经成功该活动的一员了!";
	// /**非该活动的参与人员*/
	// public static String ACTIVITY_USER_NOT_BELOND="非该活动的参与人员!";
	// /**您已经申请加入该活动，等待发布人审核*/
	// public static String ACTIVITY_USER_EVER_APPLY="您已经申请加入该活动，等待发布人审核!";
	// /**活动人数已满!*/
	// public static String ACTIVITY_USER_FULL="活动人数已满!";
	// /**非待开始状态不能进行操作!*/
	// public static String ACTIVITY_NOT_WAITING_TO_START="非待开始状态不能进行操作!";
	// /**非待开始状态不能进行操作!*/
	// public static String ACTIVITY_IS_END="活动已结束!";
	// /**发布成功*/
	// public static String publish_success="发布成功";
	// /**发布失败*/
	// public static String publish_falied="发布失败";
	// /**提交成功*/
	// public static String SUBMIT_SUCCESS="提交成功";
	// /**提交失败*/
	// public static String SUBMIT_FALIED="提交失败";
	// /**操作成功*/
	// public static String OPERATE_SUCCESS="操作成功";
	// /**操作失败*/
	// public static String OPERATE_FALIED="操作失败";
	// /**经纬度不能为空*/
	// public static String LAT_LNT_EMPTY="经纬度不能为空";
	// /**请选择要添加的好友*/
	// public static String FRIEND_SELECT_NOT_EMPTY="请选择要添加的好友！";
	// /**请选择用户*/
	// public static String BLACK_SELECT_USER_NOT_EMPTY="请选择用户！";
	// /**添加好友对象不能自己*/
	// public static String FRIEND_SELECT_ERROR="添加好友对象不能自己！";
	// /**拉黑成功*/
	// public static String BLACK_SUCCESS="拉黑成功！";
	// /**拉黑失败*/
	// public static String BLACK_FAILED="拉黑失败！";
	// /**用户已拉黑*/
	// public static String BLACK_EXIST_FAILED="用户已拉黑！";
	// /**拉黑成功*/
	// public static String BLACK_REMOVE_SUCCESS="移除拉黑成功！";
	// /**拉黑失败*/
	// public static String BLACK_REMOVE_FAILED="移除拉黑失败！";
	// /**拉黑对象不能为自己*/
	// public static String BLACK_SELECT_USER_SELECT_ERROR="拉黑对象不能为自己！";
	// /**申请成功，等待对方确认*/
	// public static String FRIEND_APPLY_SUCCESS="申请成功，等待对方确认！";
	// /**申请成功，等待对方确认*/
	// public static String FRIEND_APPLY_ERROR="申请添加好友失败！";
	// /**已经是好友*/
	// public static String FRIEND_APPLY_IS_FRIEND="已经是好友！";
	// /**对方已发送申请好友请求*/
	// public static String FRIEND_APPLY_TUSER_ADD_REQUEST="对方已发送申请好友请求！";
	// /**已发送申请好友请求*/
	// public static String FRIEND_APPLY_USER_ADD_REQUEST="已发送申请好友请求！";
	// /**已成功添加为好友*/
	// public static String FRIEND_APPLY_USER_ADD_AGGREE="已成功添加为好友！";
	// /**已成功添加为好友*/
	// public static String FRIEND_APPLY_USER_ADD_REFUSE="已拒绝添加为好友！";
	// /**非确认状态*/
	// public static String FRIEND_APPLY_USER_ADD_NOT_CONFIRM="非确认状态！";
	// /**请求错误*/
	// public static String FRIEND_APPLY_USER_ADD_NOT_CONFIRM_ERROR="请求错误！";
	// /**已经是拒绝状态*/
	// public static String FRIEND_APPLY_USER_ADD_NOT_CONFIRM_REFUSE="已经是拒绝状态！";
	// /**添加成功*/
	// public static String ADD_SUCCESS="添加成功！";
	// /**添加失败*/
	// public static String ADD_FALIED="添加失败！";
	// /**删除成功*/
	// public static String DEL_SUCCESS="删除成功！";
	// /**删除失败*/
	// public static String DEL_FALIED="删除失败！";
	// /**已添加*/
	// public static String ADD_EVER="已添加！";
	// /**动态不存在*/
	// public static String DYNAMIC_NOT_EXSIT="动态不存在！";
	// /**评论成功*/
	// public static String COMMENT_SUCCESS="评论成功！";
	// /**回复成功*/
	// public static String COMMENT_REPLY_SUCCESS="回复成功！";
	// /**回复失败*/
	// public static String COMMENT_REPLY_FAILED="回复失败！";
	// /**评论不存在*/
	// public static String COMMENT_NOT_EXIST="评论不存在！";
	// /**评论失败*/
	// public static String COMMENT_FALIED="评论失败！";
	// /**回复成功*/
	// public static String REPLY_SUCCESS="回复成功！";
	// /**回复失败*/
	// public static String REPLY_FALIED="回复失败！";
	// /**群组不存在*/
	// public static String GROUP_NOT_EXIST="群组不存在！";
	// /**当前用户不在该群组中*/
	// public static String GROUP_USER_NOT_EXIST="当前用户不在该群组中！";
	// /**不是群主没有权限*/
	// public static String GROUP_NOT_BELONG_MASTER="不是群主没有权限！";
	// /**退出群组成功*/
	// public static String GROUP_EXIT_SUCCESS="退出群组成功！";
	// /**退出群组失败*/
	// public static String GROUP_EXIT_FAILED="退出群组失败！";
	// /**礼物不存在*/
	// public static String GIFT_NOT_EXIST="礼物不存在！";
	// /**兑换成功*/
	// public static String GIFT_EXCHANGE_SUCCESS="兑换成功！";
	// /**兑换失败*/
	// public static String GIFT_EXCHANGE_FAILED="兑换失败！";
	// /**赠送成功*/
	// public static String GIFT_LARGESS_SUCCESS="赠送成功！";
	// /**赠送失败*/
	// public static String GIFT_LARGESS_FAILED="赠送失败！";
	// /**请选择要赠送的礼物！*/
	// public static String GIFT_SELECT_GIFT_ID="请选择要赠送的礼物！";
	// /**赠送人不能为空！*/
	// public static String GIFT_SELECT_GIFT_GIVE_USER_ID="赠送人不能为空！";
	// /**请选择要赠送的礼物的数量！*/
	// public static String GIFT_SELECT_GIFT_NUM="请选择要赠送的礼物的数量！";
	// /**请选择支付类型！*/
	// public static String PAY_SELECT_TYPE="请选择支付类型！";
	// /**输入的旧密码与原密码不一致！*/
	// public static String LOGIN_PWD_NOT_MATCH="输入的旧密码与原密码不一致！";
	// /**订单不存在！*/
	// public static String ORDERSN_NOT_EXIST="订单不存在！";

	public void gg() {
		System.out.println(MarkedCaption.updateOperat.提交失败);
	}
	/**
	 * 1参数 2登录 3注册 4店铺 5商品 6操作 7
	 * 
	 * @param type
	 * @return
	 */

}

enum Parameter {

	缺失参数(0), 参数错误(1), GET_SUCCESS(2), 电话号码为空(3);
	private Integer code;
	private static final Map<Integer, Parameter> MY_MAP = new HashMap<Integer, Parameter>();

	static {
		for (Parameter myEnum : values()) {
			MY_MAP.put(myEnum.getCode(), myEnum);
		}
	}

	private Parameter(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static Parameter getByValue(int value) {
		return MY_MAP.get(value);
	}
}

enum LoginParam {

	账号或密码错误(0), 登录失败(1), 登录成功(2), 用户不存在(3), 该账号已冻结(4), 请登录后查看(5), 请登录后进行操作(5);
	private static final Map<Integer, LoginParam> MY_MAP = new HashMap<Integer, LoginParam>();

	static {
		for (LoginParam myEnum : values()) {
			MY_MAP.put(myEnum.getCode(), myEnum);
		}
	}

	private Integer code;

	private LoginParam(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static LoginParam getByValue(int value) {
		return MY_MAP.get(value);
	}
}

enum RegistParam {

	注册成功(0), 注册失败(1), 成功登录(2), 用户不存在(2);
	private Integer code;
	private static final Map<Integer, RegistParam> MY_MAP = new HashMap<Integer, RegistParam>();

	static {
		for (RegistParam myEnum : values()) {
			MY_MAP.put(myEnum.getCode(), myEnum);
		}
	}

	private RegistParam(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static RegistParam getByValue(int value) {
		return MY_MAP.get(value);
	}
}

enum ShopParam {

	入驻申请成功(0);
	private Integer code;
	private static final Map<Integer, ShopParam> MY_MAP = new HashMap<Integer, ShopParam>();

	static {
		for (ShopParam myEnum : values()) {
			MY_MAP.put(myEnum.getCode(), myEnum);
		}
	}

	private ShopParam(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static ShopParam getByValue(int value) {
		return MY_MAP.get(value);
	}
}

enum GoodsParam {

	商品发布成功(0);
	private Integer code;
	private static final Map<Integer, GoodsParam> MY_MAP = new HashMap<Integer, GoodsParam>();

	static {
		for (GoodsParam myEnum : values()) {
			MY_MAP.put(myEnum.getCode(), myEnum);
		}
	}

	private GoodsParam(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static GoodsParam getByValue(int value) {
		return MY_MAP.get(value);
	}
}

enum OperatorResult {

	删除成功(0), 删除失败(1), 修改成功(2), 修改失败(3), 设置成功(4), 设置失败(5), 充值成功(6), 充值失败(7), 点赞成功(8), 点赞失败(9), 收藏成功(10), 收藏失败(11), 评论成功(
			12), 评论失败(13), 回复成功(12), 回复失败(13), 评价成功(12), 评价失败(13);
	private Integer code;
	private static final Map<Integer, OperatorResult> MY_MAP = new HashMap<Integer, OperatorResult>();

	static {
		for (OperatorResult myEnum : values()) {
			MY_MAP.put(myEnum.getCode(), myEnum);
		}
	}

	private OperatorResult(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static OperatorResult getByValue(int value) {
		return MY_MAP.get(value);
	}

}
