package dbutil.queryconfig.jsqlparser.statement.select;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.ExpressionVisitor;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ItemsList;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ItemsListVisitor;
import dbutil.tools.StringUtils;


/**
 * A subselect followed by an optional alias.
 */
public class SubSelect implements FromItem, Expression, ItemsList {

    private SelectBody selectBody;

    private String alias;

    public void accept(FromItemVisitor fromItemVisitor) {
        fromItemVisitor.visit(this);
    }

    public SelectBody getSelectBody() {
        return selectBody;
    }

    public void setSelectBody(SelectBody body) {
        selectBody = body;
    }

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String string) {
        alias = string;
    }

    public void accept(ItemsListVisitor itemsListVisitor) {
        itemsListVisitor.visit(this);
    }

    public String toString() {
        return StringUtils.concat("(",selectBody.toString(),")",alias == null?"":" ".concat(alias));
    }

	public String getWholeTableName() {
		return StringUtils.concat("(",selectBody.toString(),")");
	}
}
