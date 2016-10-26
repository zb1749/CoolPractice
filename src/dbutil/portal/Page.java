package dbutil.portal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int totalCount;//总结果数
	private List<T> list;//结果集

	public Page() {
		this(0);
	}

	public Page(int total) {
		this.totalCount = total;
	}

	public Page(int total, List<T> data) {
		this.totalCount = total;
		this.list = data;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return this.list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setList(T[] list) {
		this.list = Arrays.asList(list);
	}
}
