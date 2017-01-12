package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class DateUtil {

	private static final Logger logger = Logger.getLogger("DateUtil");

	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static Date parseDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("parse exception occur when parse date"
					+ e.getMessage());
		}
	}

	/**
	 * 
	 * 功能说明：得到当前系统日期date类型
	 * 
	 * @return
	 * 
	 */
	public static Date getDateTodaytime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.parse(df.format(new Date()));
		} catch (ParseException e) {
			logger.info(e.getMessage());
			return null;
		}

	}

	public static Date getDay(String dateString) throws Exception {
		// yyyyMMddhhmmss
		if (dateString.length() == 14) {
			return getDay(dateString, "yyyyMMddHHmmss");
		} else if (dateString.length() == 19) {
			return getDay(dateString, "yyyy-MM-dd HH:mm:ss");
		} else if (dateString.length() == 8) {
			return getDay(dateString, "yyyyMMdd");
		} else if (dateString.length() == 10) {
			return getDay(dateString, "yyyy-MM-dd");
		} else {
			return new SimpleDateFormat().parse(dateString);
		}
	}

	public static Date getDay(String dateString, String format)
			throws Exception {
		SimpleDateFormat d = new SimpleDateFormat(format);
		try {
			Date date = d.parse(dateString);
			return date;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 功能说明：得到制定格式类型的当前系统日期date类型
	 * 
	 * @return
	 * 
	 */
	public static Date getDateTodaytime(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(df.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * 功能说明：得到当前系统日期date类型
	 * 
	 * @return
	 * 
	 */
	public static Date getDateToday() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(df.format(new Date()));
		} catch (ParseException e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * 功能说明：将Date类型日期转换字符串
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static String parseDateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		return str;
	}

	/**
	 * 功能说明：将Date类型日期转换字符串
	 */
	public static String parseStrToStr2(String datestr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date d = df.parse(datestr);
			String str = parseDateToStr(d);
			return str;
		} catch (ParseException e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * 功能说明：将Date类型日期转换字符串
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static String parseDateToStr3(Date date) {
		SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String str = sdff.format(date);
		return str;
	}

	/**
	 * 中文日期格式字符串转换 yyyy年MM月
	 * 
	 * @param month
	 * @return
	 */
	public static String formatMonth(String month) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMM");
		Date date;
		try {
			date = format1.parse(month);
		} catch (ParseException e) {
			logger.info("日期转化失败");
			return null;
		}
		String str = format2.format(date);
		return str;
	}

	public static void main(String[] args) {
		System.out.println(formatMonth("2015年1月"));
	}

	/**
	 * 功能说明：将String类型日期转换字符串yyyyMMdd HH:mm
	 */
	public static String parseStrToStr(String datestr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		try {
			Date d = df.parse(datestr);
			String str = sdf.format(d);
			return str;
		} catch (ParseException e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	/**
	 * 功能说明：将String类型日期转换字符串yyyy-MM-dd
	 */
	public static String parseStrToStr4(String datestr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date d = df.parse(datestr);
			String str = sdf.format(d);
			return str;
		} catch (ParseException e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	/**
	 *  取得当前时间（格式为：yyyyMMddHHmmss）
	 */
	public static String getDateTime() {
		return getTimeFormat("yyyyMMddHHmmss");
	}

	/**
	 *  按指定格式取得当前时间()
	 */
	public static String getTimeFormat(String strFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		String sDate = sdf.format(new Date());
		return sDate;
	}

	/**
	 *  按固定yyyy-MM-dd HH:mm:ss格式取得当前时间()
	 */
	public static String getTimeFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDate = sdf.format(new Date());
		return sDate;
	}

	/**
	 * 得到当前时间的字符格式
	 */
	public static String getCurrentTime() {
		return format(new Date());
	}

	/**
	 * 返回当前日期的字符格式
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return format(new Date(), "yyyyMMdd");
	}

	/**
	 * " 格式化成系统常用日期格式：yyyyMMddHHmmss
	 */
	public static String format(Date date) {
		return format(date, "yyyyMMddHHmmss");
	}

	/**
	 * 格式化日期
	 */
	public static String format(Date date, String formatStr) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat sf = new SimpleDateFormat(formatStr);
		return sf.format(date);
	}

	/**
	 * 检查时间 的合法性
	 * 
	 * @param currentTime
	 * @param format
	 * @return
	 */
	public static boolean checkCurrentTime(String currentTime, String format) {
		try {
			Date date = DateUtil
					.parseDate(currentTime.replace("-", ""), format);
			Calendar calendar = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(new Date());
			calendar.setTime(date);
			if (calendar.before(calendar2)) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

	/**
	 * 功能说明：将String类型yyyyMMddHHmms转换yyyy-MM-dd HH:mm:ss格式
	 */
	public static String parseStringToStr(String dateStr) {
		try {
			SimpleDateFormat sdf0 = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (dateStr.length() == 8) {
				Date time = sdf0.parse(dateStr);
				return sdf2.format(time).toString();
			} else if (dateStr.length() == 14) {
				Date time = sdf.parse(dateStr);
				return sdf2.format(time).toString();
			} else {
				return dateStr;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	/**
	 * 比较开始时间和结束时间， 如果开始时间在结束时间之前则返回ture；否则返回false
	 */
	public static boolean CompareTime(String currentTime, String afterTime,
			String format) {
		try {
			Date beginTime = DateUtil.parseDate(currentTime.replace("-", ""),
					format);
			Date endTime = DateUtil.parseDate(afterTime.replace("-", ""),
					format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(beginTime);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(endTime);

			if (calendar.before(calendar2)) {
				return true;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			return false;
		}
		return false;
	}

	/** 获得指定格式日期的的秒数 */
	public static Long getFormatTime(String date) {
		Date temp = getDate(date, "yyyy-MM-dd HH:mm:ss");
		return temp.getTime();
	}

	public static Date getDate(String date, String format) {
		Date result = null;
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		try {
			result = fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getDateByFormat(String format, Date date) {
		if (format == null || StringUtil.isNull(format)) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(date);
		return sDate;
	}

	public static long getDistinceDay(Date beforedate, Date afterdate) {
		return (afterdate.getTime() - beforedate.getTime())
				/ (24 * 60 * 60 * 1000);
	}

	/**
	 * 将14位到转化为19位 yyyy-MM-dd HH:mm:ss
	 */
	public static String format14to19(String dateTime) {
		if (null == dateTime) {
			return null;
		}
		return format14(dateTime, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到 14位：yyyyMMddHHmmss
	 * 
	 * @param dateTime
	 * @param format
	 * @return
	 */
	public static String format14(String dateTime, String format) {
		SimpleDateFormat source = new SimpleDateFormat("yyyyMMddHHmmss");
		Date day = null;
		try {
			day = source.parse(dateTime);
		} catch (ParseException e) {
			return dateTime;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(day);
		return sDate;
	}

	/**
	 * 检查当前时间是否在指定的时间段内
	 * 
	 * @param beginTime
	 *            起始时间(HH:mm)
	 * @param endTime
	 *            终止时间(HH:mm)
	 * @return
	 * @author cWX171829
	 */
	public static boolean checkTimeInRange(String beginTime, String endTime) {
		boolean result = false;
		String sysDateTime = DateUtil.getDate(DateUtil.FORMAT_YYYY_MM_DD);
		String sysDate = sysDateTime.split("\\s")[0];
		// String currentTime = currentDateTime.split("\\s")[1];
		// currentDateTime = currentTime.substring(0,
		// currentTime.lastIndexOf(':'));
		String beginDateTime = sysDate + " " + beginTime + ":00";
		String endDateTime = sysDate + " " + endTime + ":00";
		Date currentDate = DateUtil.getDate(sysDateTime,
				DateUtil.FORMAT_YYYY_MM_DD);
		Date beginDate = DateUtil.getDate(beginDateTime,
				DateUtil.FORMAT_YYYY_MM_DD);
		Date endDate = DateUtil
				.getDate(endDateTime, DateUtil.FORMAT_YYYY_MM_DD);
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(currentDate);
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(beginDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		// 当终止时间小于起始时间时，终止时间日期加1
		if (endCalendar.before(beginCalendar)) {
			endCalendar.add(Calendar.DATE, 1);
			// 当当前时间小于开始时间时，把当前时间日期加1
			if (currentCalendar.before(beginCalendar)) {
				currentCalendar.add(Calendar.DATE, 1);
			}
		}
		// 当终止时间等于起始时间或当前时间大于起始时间而小于终止时间时，返回true
		if (endCalendar.compareTo(beginCalendar) == 0
				|| (currentCalendar.after(beginCalendar) && currentCalendar
						.before(endCalendar))) {
			return true;
		}
		return result;
	}

	/**
	 * 取得当前日期
	 * 日期格式，默认为yyyy-MM-dd
	 * @return
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	public static String getDate(String format) {
		if (format == null || StringUtil.isNull(format)) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(new Date());
		return sDate;
	}

	/**
	 * 取得当前时间（格式为：yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTimeDefaultFormat() {
		return getTimeFormat("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 验证两个日期的间隔不大于给定的scope
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param scope
	 * @param format
	 * @return
	 */
	public static boolean checkTwoDateInScope(String beginDate, String endDate,
			int scope, String format) {
		try {
			Date beginTime = DateUtil.parseDate(beginDate, format);
			Date endTime = DateUtil.parseDate(endDate, format);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(beginTime);
			calendar.add(Calendar.DAY_OF_YEAR, scope);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(endTime);

			if (!calendar.before(calendar2)) {
				return true;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			return false;
		}
		return false;
	}

	/**
	 * 获得指定格式的当前日期
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentTime(String format) {
		return format(new Date(), format);
	}

	/**
	 * 获取当前日期之前或之后几天的时间string(指定时间格式)
	 * 
	 * @param days
	 * @param format
	 * @return
	 */
	public static String getAnotherDay(int days, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return format(calendar.getTime(), format);
	}

	public static String getCurrentMilliSecond(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS") .format(new Date() );
	}
}
