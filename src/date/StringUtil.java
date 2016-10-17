package date;

public class StringUtil {
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
	 * <一句话功能简述> 判断传入的参数是否为null，空字符串，或者无有效字符 <功能详细描述>
	 * 判断传入的参数是否为null，空字符串，或者无有效字符
	 * 
	 * @param strSrc
	 * @return [参数说明]
	 * 
	 * @return boolean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isEmpty(String strSrc) {
		if ((null == strSrc)
				|| (strSrc.length() < 1 || (strSrc.trim().length() < 1))) {
			return true;
		} else {
			return false;
		}
	}
}
