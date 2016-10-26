package dbutil.common;

import java.io.Serializable;


/**
 * 描述一个范围
 */
public interface Range<T extends Comparable<T>> extends Serializable {
	/**
	 * 得到范围中最大的值
	 * @return
	 */
	public abstract T getGreatestValue();

	/**
	 * 得到范围中最小的值
	 * @return
	 */
	public abstract T getLeastValue();

	/**
	 * 描述范围是否连续
	 * @return
	 */
	public abstract boolean isContinuous();
	
	/**
	 * 判断给定的值是否在范围中
	 * @param obj
	 * @return
	 */
	public abstract boolean contains(T obj);
}
