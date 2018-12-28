package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间处理类
 * 
 * 
 */
public class UtilTime {
	/**
	 * 默认得到当前时间 传入时间格式
	 * 
	 * @param timeFormat
	 *            格式
	 * @return String 返回值
	 * @throws Exception
	 */
	public static String getTime(String timeFormat) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat(timeFormat);
		return sf.format(new Date());
	}

	/**
	 * 得到两个时间相减的毫秒数
	 * 
	 * @param startDate
	 * @param endDate
	 * @param timeFormat
	 * @return long
	 * @throws Exception
	 */
	private static long getTime(String startDate, String endDate,
			String timeFormat) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat(timeFormat);
		Date endTime = sf.parse(startDate);
		Date startTime = sf.parse(endDate);
		return endTime.getTime() - startTime.getTime();
	}

	/**
	 * 得到两个时间相减
	 * 
	 * @param startDate
	 * @param endDate
	 * @param timeFormat
	 * @return 格式 X天X小时
	 */
	public static String getJianDay(String startDate, String endDate,
			String timeFormat) throws Exception {
		if (!UtilValiDate.isEmpty(endDate)) {
			return "0天0小时";
		}
		long different = getTime(startDate, endDate, timeFormat);
		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;
		long elapsedDays = different / daysInMilli;
		different = different % daysInMilli;
		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;
		return elapsedDays + "天" + elapsedHours + "小时";
	}

	/**
	 * 给入时间间隔参数 得到间隔之后的时间
	 * 
	 * @param timeFormat
	 *            格式化参数
	 * @param tiemNum
	 *            间隔参数 毫秒
	 * @return String
	 */
	public static String getAgoTime(String timeFormat, long tiemNum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(cal.getTime().getTime() - tiemNum));
		return new SimpleDateFormat(timeFormat).format(cal.getTime());// 获取到完整的时间
	}

	/**
	 * 获取时间
	 * 
	 * @param dayNum
	 *            负数为今天之后的时间 正数为今天之前的时间
	 * @return String yyyy-MM-dd
	 * @throws Exception
	 */
	public static String getDayData(int dayNum) throws Exception {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
	
	/**
	 * 比较两个时间是否相等 (比较格式 yyyyMMdd)  结束时间大于当前时间返回true 小于当前时间返回false
	 * @param yyyy_mm_dd 结束时间
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean timeCompare(Object yyyy_mm_dd)throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String startTime = formatter.format(new Date());
		String stopTime = UtilStr.getStr(yyyy_mm_dd).replaceAll("-", "");
		return UtilStr.getInt(stopTime)>=UtilStr.getInt(startTime);
	}
	
	
	/**
	 * 比较两个时间是否相等 (比较格式 yyyy_mm_dd_hh_mm_ss)  结束时间大于当前时间返回true 小于当前时间返回false
	 * @param yyyy_mm_dd 结束时间
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean timeCompare1(Object yyyy_mm_dd_hh_mm_ss)throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = formatter.format(new Date());
		String stopTime = UtilStr.getStr(yyyy_mm_dd_hh_mm_ss).replaceAll("-", "").replace(":", "").replace(" ", "");
		return UtilStr.getInt(stopTime)>=UtilStr.getInt(startTime);
	}

}
