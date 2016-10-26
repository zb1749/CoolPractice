package dbutil.queryconfig.jsqlparser;

import java.util.List;

import dbutil.queryconfig.jsqlparser.statement.select.Distinct;
import dbutil.queryconfig.jsqlparser.statement.select.SelectExpressionItem;
import dbutil.queryconfig.jsqlparser.statement.select.SelectItem;
import dbutil.queryconfig.jsqlparser.statement.select.SelectItemVisitor;


/**
 * 负责将转换为count中的查询项
 * @author Administrator
 *
 */
public class CountSelectItemConverter implements SelectItem {
	private Distinct dis;
	private List<SelectItem> items;
	
	public CountSelectItemConverter(List<SelectItem> items, Distinct dis){
		this.items=items;
		this.dis=dis;
	}

	public void accept(SelectItemVisitor selectItemVisitor) {
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("count(");
		if (dis != null) {
			sb.append(dis.toString()).append(' ');
			int n = 0;
			for (SelectItem v : items) {
				if (n > 0)
					sb.append("||");
				if(v instanceof SelectExpressionItem){
					String alias=((SelectExpressionItem) v).getAlias();
					((SelectExpressionItem) v).setAlias(null);
					sb.append(v.toString());
					((SelectExpressionItem) v).setAlias(alias);
				}else{
					sb.append(v.toString());
				}
				n++;
			}
		} else {
			sb.append("*");
		}
		sb.append(")");
		return sb.toString();
	}
}
