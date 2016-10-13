package io.ftp;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtil {


	/**
	 * 字段分隔符
	 */
    public final static String COMPARTMENTATION="|";
    
    /**
     * 对字段值进行转换
     * @param value
     * @return
     */
    public static String translate(String value)
    {
        value = changeNull(value);
            
        //将换行转换成空格
        value = value.replace("\r\n", " ");
        value = value.replace("\r", " ");
        value = value.replace("\n", " ");
        
        value = value.replace("|", " ");//将"|"换成空格
        return value;
    }
    
    
	public static boolean isNotNull(String str) {
		return !(str == null || (str.trim().length() == 0));
	}

	/**
	 * 判断字符串是否为空或全为空格
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return (str == null || (str.trim().length() == 0));
	}

	/**
	 * 判断传入的参数是否为null，空字符串，或者无有效字符
	 */
	public static boolean isEmpty(String strSrc) {
		if ((null == strSrc)
				|| (strSrc.length() < 1 || (strSrc.trim().length() < 1))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 若为null,则返回""，否则返回原值
	 */
	public static String changeNull(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 清除掉所有特殊字符
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String str) throws PatternSyntaxException {
		String regEx = "[;\\\\＼；、,，]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("|").trim();
	}

	/**
	 * 对于用;做分割符的话单需替换为空，以及替换换行符回车符，去除前后空格
	 * 
	 * @param str
	 * @return [参数说明]
	 * 
	 */
	public static String replaceOther(String str) {
		if (null == str) {
			return "";

		}
		str = str.trim();

		str = str.replaceAll("\n", "").replaceAll("\r", "").replaceAll(";", "");

		return str;
	}

	/**
	 * 去除空格换行符
	 * 
	 * @return
	 */
	public static String replaceBlank(String str) {
		if (null == str) {
			return "";
		}
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");

		Matcher m = p.matcher(str);

		String after = m.replaceAll("");

		return after;
	}

	public static String nvl(Object o) {
		return (null == o) ? "" : o.toString().trim();
	}

	/**
	 * 判断两个字符串是否相等
	 */
	public static boolean isEq(String str, String other) {
		if (str == null) {
			return other == null;
		}
		return str.equals(other);
	}

	/**
	 * 
	 * 重复去除开头首字
	 * 
	 * @author hKF77999
	 * @param str
	 * @param remove
	 *            首字
	 * @return
	 */
	public static String removeStart(String str, String remove) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		while (str.startsWith(remove)) {
			str = str.substring(remove.length());
		}

		return str;
	}

	/**
	 * 去掉路径,只返回文件名称
	 * 
	 * @author qWX145165
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		String strRet = "";
		int index = 0;
		if (isNotNull(fileName)) {
			index = fileName.lastIndexOf("/");
			if (index > 0) {
				strRet = fileName.substring(index + 1, fileName.length());
			} else {
				strRet = fileName;
			}
		}

		return strRet;
	}

	/**
	 * 单位转换分->元 此方法可重写，多带参数，这里有不必要的判断
	 */
	public static String getCastUnits(int value) {
		String castValue = "";
		if (value != -1) {
			int scale = 2;

			BigDecimal b1 = new BigDecimal(Integer.toString(value));

			BigDecimal b2 = new BigDecimal("100");

			castValue = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP)
					.toString();
		}
		return castValue;
	}

	/**
	 * 单位转换 此方法可重写，多带参数，这里有不必要的判断
	 */
	public static String getCastUnits(String value, String div) {
		String castValue = "";
		int scale = 2;

		//BigDecimal b1 = new BigDecimal(Integer.parseInt(value));
		BigDecimal b1 = new BigDecimal(Long.parseLong(value));
		
		BigDecimal b2 = new BigDecimal(div);

		castValue = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
		return castValue;
	}

	public static Integer getMinUnits(String v1, String v2) {
		if (null == v1 || "".equals(v1.trim())) {
			return -1;
		}
		BigDecimal b1 = new BigDecimal(v1);

		BigDecimal b2 = new BigDecimal(v2);

		return b1.multiply(b2).intValue();

	}

	/**
	 * 替换字符串中的特殊字符
	 * 
	 * @param orignString
	 * @return
	 */
	public static String replace(String orignString) {
		if (StringUtil.isNull(orignString)) {
			return " ";
		}
		return orignString.replace(",", "，").replace('\n', ' ')
				.replace('\r', ' ');
	}
	
	/**
     * 正则校验
     * @param str 校验字符串  regex 正则表达式
     * @return true 匹配成功 false 不匹配
     */
    public static boolean matchRegex(String str,String regex)
    {
        if (null == str || null == regex)
        {
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    
    /**
     * 特殊字符处理
     * @param orient
     * @return
     */
    public static String replaceChracters(String orient)
    {
        if (null != orient && orient.length() > 0)
            return orient.replace('\r', ' ').replace('\n', ' ');
        else
        {
            return "";
        }
    }

}
