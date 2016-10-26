package dbutil.queryconfig.jsqlparser.expression.operators.relational;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.ExpressionVisitor;

public class InExpression implements Expression {

    private Expression leftExpression;
  //变量绑定值是否为空
    private ThreadLocal<Boolean> isEmpty = new ThreadLocal<Boolean>();
    
    public boolean isEmpty() {
		return isEmpty.get()==null?false:isEmpty.get();
	}
    
    private ItemsList itemsList;

    private boolean not = false;

    public InExpression() {
    }

    public InExpression(Expression leftExpression, ItemsList itemsList) {
        setLeftExpression(leftExpression);
        setItemsList(itemsList);
    }

    public ItemsList getItemsList() {
        return itemsList;
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public void setEmpty(boolean isEmpty) {
		this.isEmpty.set(isEmpty);
	}
    
    public void setItemsList(ItemsList list) {
        itemsList = list;
    }

    public void setLeftExpression(Expression expression) {
        leftExpression = expression;
    }

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean b) {
        not = b;
    }

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public String toString() {
        return leftExpression + " " + ((not) ? "NOT " : "") + "IN " + itemsList + "";
    }
}
