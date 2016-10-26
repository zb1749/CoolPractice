package dbutil.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * 用于提供各种线程安全的时间日期格式
 * @author Administrator
 *
 */
public abstract class DateFormats {
	/** 日期格式：美式日期 MM/DD/YYYY*/
	public static final ThreadLocal<DateFormat> DATE_US = new ThreadLocal<DateFormat>(){
		protected DateFormat initialValue() {
			return new SimpleDateFormat("MM/dd/yyyy");
		}
	};
	//支持 yyyy-m-d 格式的
	//非严格模式正则
	public static final String DATE_CS_REGEXP="((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(10|12|0?[13578])([-\\/\\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(11|0?[469])([-\\/\\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(0?2)([-\\/\\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([3579][26]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$))";
	//严格模式正则
	public static final String DATE_CS_REGEXP_STRICT="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
	//严格模式正则
	/** 日期格式：美式日期+时间 MM/DD/YYYY HH:MI:SS */
	public static final ThreadLocal<DateFormat> DATE_TIME_US = new ThreadLocal<DateFormat>(){
		
		protected DateFormat initialValue() {
			return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		}
	};
	/** 日期格式：中式日期 YYYY-MM-DD */
	public static final ThreadLocal<DateFormat> DATE_CS = new ThreadLocal<DateFormat>(){
		
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	/** 日期格式：日期+时间 YYYY/MM/DD */
	public static final ThreadLocal<DateFormat> DATE_CS2 = new ThreadLocal<DateFormat>(){
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd");
		}
	};
	
	/** 日期格式：日期+时间 YYYY-MM-DD HH:MI:SS */
	public static final ThreadLocal<DateFormat> DATE_TIME_CS = new ThreadLocal<DateFormat>(){
		
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	
	/** 日期格式：日期+时间 YYYY/MM/DD HH:MI:SS */
	public static final ThreadLocal<DateFormat> DATE_TIME_CS2 = new ThreadLocal<DateFormat>(){
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}
	};
	
	/** 日期格式：中式日期时间（到分） YYYY-MM-DD HH:MI */
	public static final ThreadLocal<DateFormat> DATE_TIME_ROUGH = new ThreadLocal<DateFormat>(){
		
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
	};
	/** 日期格式：中式日期+时间戳 YYYY-MM-DD HH:MI:SS.SSS */
	public static final ThreadLocal<DateFormat> TIME_STAMP_CS = new ThreadLocal<DateFormat>(){
		
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		}
	};
	/** 日期格式：仅时间 HH.MI.SS */
	public static final ThreadLocal<DateFormat> TIME_ONLY = new ThreadLocal<DateFormat>(){
		
		protected DateFormat initialValue() {
			return new SimpleDateFormat("HH:mm:ss");
		}
	};
	/** 日期格式：日期紧凑 YYYYMMDD */
	public static final ThreadLocal<DateFormat> DATE_SHORT = new ThreadLocal<DateFormat>(){
		
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}
	};
	/** 日期格式：日期时间紧凑 YYYYMMDDHHMISS */
	public static final ThreadLocal<DateFormat> DATE_TIME_SHORT_14 = new ThreadLocal<DateFormat>(){
		
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		}
	};
	/** 日期格式：日期时间紧凑 YYYYMMDDHHMI */
	public static final ThreadLocal<DateFormat> DATE_TIME_SHORT_12 = new ThreadLocal<DateFormat>(){
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmm");
		}
	};
	/** 日期格式：yyyyMM */
	public static final ThreadLocal<DateFormat> YAER_MONTH = new ThreadLocal<DateFormat>(){
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMM");
		}
	};
}
