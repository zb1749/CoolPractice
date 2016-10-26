package dbutil.portal;

import java.util.LinkedHashMap;

public class OrderByElement {

	/** 
	 * 如果orderMap不为空，则升降序以此参数为准进行SQL构造
	 *  orderMap的key即排序字段名，value为ASC或者DESC
	 */
	private LinkedHashMap<String,String> orderMap = null;
	private String[] orderByField;
	public final static String ASC = "ASC";
	public final static String DESC = "DESC";
	private String orderByType=DESC;
	
	/** 
	 *  orderMap的key即排序字段名，value为ASC或者DESC
	 */
	public OrderByElement(LinkedHashMap<String,String> orderMap) {
		this.orderMap=orderMap;
	}
	
	public OrderByElement(String orderByField,String orderByType) {
		String[] tmp = {orderByField};
		this.orderByField=tmp;
		this.orderByType=orderByType;
	}
	
	public OrderByElement(String[] orderByField,String orderByType) {
		this.orderByField=orderByField;
		this.orderByType=orderByType;
	}
	public String[] getOrderByField() {
		return orderByField;
	}
	public void setOrderByField(String[] orderByField) {
		this.orderByField = orderByField;
	}
	public String getOrderByType() {
		return orderByType;
	}
	public void setOrderByType(String orderByType) {
		this.orderByType = orderByType;
	}

	/** 
	 *  orderMap的key即排序字段名，value为ASC或者DESC
	 */
	public LinkedHashMap<String, String> getOrderMap() {
		return orderMap;
	}
}
