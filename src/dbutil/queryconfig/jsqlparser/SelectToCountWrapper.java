package dbutil.queryconfig.jsqlparser;

import java.util.Arrays;

import dbutil.queryconfig.jsqlparser.expression.Function;
import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;
import dbutil.queryconfig.jsqlparser.statement.select.SelectExpressionItem;
import dbutil.queryconfig.jsqlparser.statement.select.SelectItem;
import dbutil.queryconfig.jsqlparser.statement.select.SubSelect;
import dbutil.queryconfig.jsqlparser.statement.select.Union;


public class SelectToCountWrapper extends PlainSelect {

	public SelectToCountWrapper(Union union) {
		buildSelectItem();

		SubSelect from = new SubSelect();
		from.setSelectBody(union);// TODO，最好去掉union中的order
		this.fromItem = from;
	}

	public SelectToCountWrapper(PlainSelect select) {
		/*
		 * 原来的实现是：直接将count包装在select item的外面，如 select count(distinct a,b) from
		 * 这种方法的问题是：当原查询是distinct时，不能适应所有数据库，如Oracle是需要将原select item变为||，
		 * 即select count(distinct a||b) from ...
		 * 因此为了适应不同的数据库，此处修改为在原SQL语句外包装一层count(*)的实现方式。
		 */

		buildSelectItem();

		SubSelect from = new SubSelect();
		from.setSelectBody(select);
		from.setAlias("tt"); // MySQL必须有别名，否则error 1248
		this.fromItem = from;
	}

	private void buildSelectItem() {
		Function count = new Function();
		count.setName("count");
		count.setAllColumns(true);
		SelectExpressionItem countItem = new SelectExpressionItem();
		countItem.setExpression(count);
		countItem.setAlias("count");
		this.selectItems = Arrays.asList((SelectItem) countItem);
	}

}
