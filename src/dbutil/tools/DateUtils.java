package dbutil.tools;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


/**
 * utils for date.
 */
public abstract class DateUtils {
	/**
	 * 转换为java.sql.Date
	 * @param d
	 * @return
	 */
	public static java.sql.Date toSqlDate(Date d) {
		if (d == null)
			return null;
		return new java.sql.Date(d.getTime());
	}

	/**
	 * 自动解析日期格式，
	 * 支持:中式日期、中式日期时间 yyyy-mm-dd
	 *      美式日期、美式日期时间 MM/dd/yyyy HH:mm:ss
	 *      8位数字日期
	 *      14位数字日期时间
	 *      12位数字时间：yyyyMMddHHmm无秒数
	 *      毫秒数
	 * @param dateStr
	 * @return
	 */
	public static Date autoParse(String dateStr) {
		try {
			int indexOfDash=dateStr.indexOf('-');
			if ( indexOfDash> 0) {// 按中式日期格式化(注意，首位为‘-’可能是负数日期，此处不应处理)
				if(indexOfDash==2){//尝试修复仅有两位数的年
					int year=StringUtils.toInt(dateStr.substring(0,indexOfDash), -1);
					if(year>=50){
						dateStr="19"+dateStr;
					}else if(year>=0){
						dateStr="20"+dateStr;
					}
				}
				if (dateStr.indexOf(':') > -1) {// 带时间
					return DateFormats.DATE_TIME_CS.get().parse(dateStr);
				} else {
					return DateFormats.DATE_CS.get().parse(dateStr);
				}
			} else if (dateStr.indexOf('/') > -1) {// 按美式日期格式化
				if (dateStr.indexOf(':') > -1) {// 带时间
					return DateFormats.DATE_TIME_US.get().parse(dateStr);
				} else {
					return DateFormats.DATE_US.get().parse(dateStr);
				}
			} else if (dateStr.length() == 8 && StringUtils.isNumeric(dateStr) && (dateStr.startsWith("19") || dateStr.startsWith("20"))) {// 按8位数字格式化
				return DateFormats.DATE_SHORT.get().parse(dateStr);
			} else if (dateStr.length() == 14 && StringUtils.isNumeric(dateStr)&& (dateStr.startsWith("19") || dateStr.startsWith("20"))) {// 按14位数字格式化yyyyMMDDHHMMSS
				return DateFormats.DATE_TIME_SHORT_14.get().parse(dateStr);
			} else if (dateStr.length() == 12 && StringUtils.isNumeric(dateStr)&& (dateStr.startsWith("19") || dateStr.startsWith("20"))) {// 按14位数字格式化yyyyMMDDHHMM
				return DateFormats.DATE_TIME_SHORT_12.get().parse(dateStr);
			} else if (StringUtils.isNumericOrMinus(dateStr)) {
				long value=Long.valueOf(dateStr).longValue();
				return new Date(value);
			} else {
				return null;
			}
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 转换为java.sql.timestamp
	 * @param d
	 * @return
	 */
	public static Timestamp toSqlTimeStamp(Date d) {
		if (d == null)
			return null;
		return new Timestamp(d.getTime());
	}
}
