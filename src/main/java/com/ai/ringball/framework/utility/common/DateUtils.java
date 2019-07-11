package com.ai.ringball.framework.utility.common;

import com.ai.ringball.framework.constants.SysConstants;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

	/**
	 * 获得日期时间字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDatetime(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformat.format(date);
	}

	/**
	 * 获得日期时间字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyyMMddHHmmss
	 */
	public static String getStrDatetime(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateformat.format(date);
	}

	/**
	 * 获得年份字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy
	 */
	public static String getYear(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy");
		return dateformat.format(date);
	}

	/**
	 * 获得年份月份字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy-MM
	 */
	public static String getYearMonth(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM");
		return dateformat.format(date);
	}

	/**
	 * 获得日期时间字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDatetime(Timestamp date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformat.format(date);
	}

	/**
	 * 获得日期时间字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy/MM/dd
	 */
	public static String getDate2(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		return dateformat.format(date);
	}

	/**
	 * 获得日期字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyyMMdd
	 */
	public static String getDate3(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		return dateformat.format(date);
	}

	/**
	 * 获得中文日期时间字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy年MM月dd日 HH时mm分ss秒
	 */
	public static String getDatetimeCN(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return dateformat.format(date);
	}

	/**
	 * 获得日期字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy-MM-dd
	 */
	public static String getDate(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		return dateformat.format(date);
	}

	/**
	 * 获得中文日期字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy年MM月dd日
	 */
	public static String getDateCN(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日");
		return dateformat.format(date);
	}

	/**
	 * 获得日期时间字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy/MM/dd HH:mm:ss
	 * @throws ParseException
	 */
	public static Date parse2Datetime2(String date) throws ParseException {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateformat.parse(date);
	}

	/**
	 * 获得日期时间字符串
	 * 
	 * @param code
	 * @return EncryptCode格式：yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 */
	public static Date parse2Datetime(String date) throws ParseException {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformat.parse(date);
	}

	/**
	 * 获得日期时间字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy/MM/dd
	 * @throws ParseException
	 */
	public static Date parse2Date2(String date) throws ParseException {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
		return dateformat.parse(date);
	}

	/**
	 * 获得日期字符串
	 * 
	 * @param code
	 * @return EncryptCode 格式：yyyy-MM-dd
	 * @throws ParseException
	 */
	public static Date parse2Date(String date) throws ParseException {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		if (date.length() == 8) {
			date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
		}
		return dateformat.parse(date);
	}

	/**
	 * 获得给定日期是星期几
	 * 
	 * @param date
	 * @return String（星期几）
	 */
	public static String getWeekNum(String date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdw = new SimpleDateFormat("E");
		Date d = null;
		try {
			d = sd.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdw.format(d);
	}

	/**
	 * 功能：比较两个字符串类型的日期大小
	 * 
	 * @param DATE1(String)
	 * @param DATE2(String)
	 * @return 1:DATE1在DATE2之后 0:DATE1与DATA2相等；-1:DATE1在DATE2之前
	 * @throws ParseException
	 */
	public static int compare_date(String DATE1, String DATE2) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dt1 = df.parse(DATE1);
		Date dt2 = df.parse(DATE2);
		if (dt1.getTime() > dt2.getTime()) {
			return 1;
		} else if (dt1.getTime() < dt2.getTime()) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * 功能：获取两个日期之间相差的天数
	 * 
	 * @param DATE1(String)
	 * @param DATE2(String)
	 * @return 两个日期之间相差的天数（绝对值）
	 * @throws ParseException
	 */
	public static int countDays(String DATE1, String DATE2) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dt1 = df.parse(DATE1);
		Date dt2 = df.parse(DATE2);
		long days = (dt1.getTime() - dt2.getTime()) / (1000 * 60 * 60 * 24);
		return Math.abs((int) days);
	}

	public static String computingTime() {
		String date = SysConstants.CONSTANT_NULL_STRING;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calender = Calendar.getInstance();
		Date curDate = new Date();
		calender.setTime(curDate);
		Calendar cMouth = Calendar.getInstance();
		cMouth.set(Calendar.DATE, 1);
		cMouth.roll(Calendar.DATE, -1);
		int day = cMouth.get(Calendar.DATE);
		calender.set(Calendar.DAY_OF_YEAR, calender.get(Calendar.DAY_OF_YEAR) - day);
		date = dateFormat.format(calender.getTime());
		return date;
	}

	public static String computingTime(int period) {
		String date = SysConstants.CONSTANT_NULL_STRING;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calender = Calendar.getInstance();
		Date curDate = new Date();
		calender.setTime(curDate);
		if (period == 3) {
			// 一个月以内
			Calendar cMouth = Calendar.getInstance();
			cMouth.set(Calendar.DATE, 1);
			cMouth.roll(Calendar.DATE, -1);
			int day = cMouth.get(Calendar.DATE);
			calender.set(Calendar.DAY_OF_YEAR, calender.get(Calendar.DAY_OF_YEAR) + day - 1);
			date = dateFormat.format(calender.getTime());
		} else if (period == 4) {
			// 年
			Calendar cYear = Calendar.getInstance();
			int currentYear = cYear.get(Calendar.YEAR);
			if ((currentYear % 4 == 0 && currentYear % 100 != 0) || currentYear % 400 == 0) {
				calender.set(Calendar.DAY_OF_YEAR, calender.get(Calendar.DAY_OF_YEAR) + 366 - 1);
			} else {
				calender.set(Calendar.DAY_OF_YEAR, calender.get(Calendar.DAY_OF_YEAR) + 365 - 1);
			}
			date = dateFormat.format(calender.getTime());
		} else if (period == 0) {
			// 上一天
			calender.setTime(curDate);
			calender.set(Calendar.DAY_OF_YEAR, calender.get(Calendar.DAY_OF_YEAR) - 1);
			date = dateFormat.format(calender.getTime());
		} else if (period == 5) {
			calender.set(Calendar.DAY_OF_YEAR, calender.get(Calendar.DAY_OF_YEAR));
			date = dateFormat.format(calender.getTime());
		} else {
			// 加指定天数
			calender.set(Calendar.DAY_OF_YEAR, calender.get(Calendar.DAY_OF_YEAR) + period - 1);
			date = dateFormat.format(calender.getTime());
		}
		return date;
	}

	public static String getStartDay(Date dtDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dtDate);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
		String startDay = dateFormat.format(calendar.getTime());
		return startDay;
	}

	/**
	 * 功能说明:获取传入日期的当前月末自然日
	 * 
	 * @param date 传入日期
	 * @return 当前月末自然日
	 */
	public static Date getEndDayOfMonth(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		try {
			return DateUtils.parse2Date2(DateUtils.getDate2(c.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
	}

	/**
	 * 功能说明:获取传入日期的下个月末自然日
	 * 
	 * @param date 传入日期
	 * @return 下个月末自然日
	 */
	public static Date getEndDayOfNextMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 2);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date result = calendar.getTime();
		try {
			return DateUtils.parse2Date2(DateUtils.getDate2(result));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 功能说明:获取传入日期的上个月末自然日
	 * 
	 * @param date 传入日期
	 * @return 上个月末自然日
	 */
	public static String getEndDayOfBeforeMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date before = calendar.getTime();
		// 按格式输出
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(before);
	}

	/**
	 * 功能说明:获取传入日期的上个月
	 * 
	 * @param date 传入日期
	 * @return 上个月(YYYYMM)
	 */
	public static String getEndMonthOfBeforeMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date before = calendar.getTime();
		// 按格式输出
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(before);
	}

	/***
	 * 得到本季初日期
	 * 
	 * @return date
	 */
	public static Date getCurrentQuarterStartTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = DateUtils.parse2Date2(DateUtils.getDate2(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 上一季度末日期
	 *
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date getEndDayOfLastQuarter(Date nowDate) {
		if (nowDate == null) {
			nowDate = new Date();
		}
		Calendar c = Calendar.getInstance();
		int curMounth = nowDate.getMonth();
		Date date = null;
		try {
			if (curMounth >= 0 && curMounth <= 2) {
				c.setTime(new Date());
				c.add(Calendar.YEAR, -1);
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			} else if (curMounth >= 3 && curMounth <= 5) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (curMounth >= 6 && curMounth <= 8) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (curMounth >= 9 && curMounth <= 11) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			}
			date = DateUtils.parse2Date2(DateUtils.getDate2(c.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * 获得本季度末日期
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date getCurrentQuarterEndTime(Date nowDate) {
		Calendar c = Calendar.getInstance();
		int currentMonth = nowDate.getMonth() + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = DateUtils.parse2Date2(DateUtils.getDate2(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}
}
