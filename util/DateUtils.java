package com.eb.dianlianbao_server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期操作工具类
 *
 * @author Administrator
 *
 */
public class DateUtils {

	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
	public static final String YEAR_AND_MONTH = "yyyy-MM";

	/**
	 * 日期格式字符串转换成时间戳
	 *
	 * @param date
	 *            字符串日期
	 * @param format
	 *            默认使用：yyyy-MM-dd
	 * @return String
	 */
	public static String dateToTimestamp(String dateStr, String format) {
		try {
			if (StringUtils.isBlank(format)) {
				format = DEFAULT_FORMAT;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取当前时间时间戳(秒)
	 *
	 * @return long
	 */
	public static long getCurrentTimestamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 获取当前时间时间戳字符串（秒）
	 *
	 * @return String
	 */
	public static String getCurrentTimestampStr() {
		return System.currentTimeMillis() / 1000 + "";
	}

	/**
	 * 获取计算后的日期时间戳
	 * 计算天数
	 * @param timestamp
	 * @param num
	 * @return
	 */
	public static String getCalcTime(long timestamp, Integer num) {
		long time = timestamp + num * 24 * 60 * 60 * 1000;
		return time + "";
	}

	/**
	 * 计算分钟
	 * <p>获取计算后的时间戳(秒)</p>
	 * @param timestamp
	 * @param num
	 * @return time
	 */
	public static long getCalMinute(long timestamp, Integer num) {
		return timestamp + num;
	}

	/**
	 * 把符合日期格式的字符串转换为日期类型
	 */
	public static Date stringtoDate(String dateStr, String format) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr);
		} catch (Exception e) {
			// log.error(e);
			d = null;
		}
		return d;
	}

	/**
	 * 把日期转换为字符串
	 */
	public static String dateToString(Date date, String format) {
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			// log.error(e);
		}
		return result;
	}

	/**
	 * 判断是否未有效日期
	 * @param str
	 * @return boolean
	 */
	public static boolean isValidDate(String str, String fmt) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		if (StringUtils.isBlank(fmt)) {
			fmt = DEFAULT_FORMAT;
		}
		boolean flag = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			flag = false;
		}
		return flag;
	}

	/**
	 * 格式化日期
	 * <p>时间戳格式为字符串日期</p>
	 * @param time
	 * @param fmt
	 * @return
	 */
	public static String formatToString(long time, String fmt) {
		if (StringUtils.isBlank(fmt)) {
			fmt = DEFAULT_FORMAT;
		}
		SimpleDateFormat format = new SimpleDateFormat(fmt);
		Date date = new Date(time);
		return format.format(date);
	}

}
