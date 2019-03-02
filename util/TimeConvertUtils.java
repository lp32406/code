package com.eb.dianlianbao_server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeConvertUtils {
	/**
	 * 转换时间�?
	 * @param date
	 * @return
	 */
	public static String convertToTimeStamp(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt1;
		try {
			dt1 = sdf.parse(date);
			long t = dt1.getTime() / 1000;
			return String.valueOf(t);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "-1";
	}
	
	/**
	 * 获取零点时间�?
	 * @param date
	 * @return
	 */
	public static Long getZeroTimeUnix() {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
		return zero.getTime()/1000;
	}

	public static String convertToTimeStamp(String date,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dt1;
		try {
			dt1 = sdf.parse(date);
			long t = dt1.getTime() / 1000;
			return String.valueOf(t);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "-1";
	}

	
	public static Date getDestinDay(Date date,int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }
	
	public static String timeDate(Long time ,String frm){
		SimpleDateFormat format = new SimpleDateFormat(frm);
		return format.format(time);
	}
	
	
}
