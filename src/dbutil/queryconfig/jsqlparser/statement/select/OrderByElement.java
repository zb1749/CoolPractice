package dbutil.queryconfig.jsqlparser.statement.select;

import dbutil.queryconfig.jsqlparser.expression.Expression;

/**
 * An element (column reference) in an "ORDER BY" clause.
 */
public class OrderByElement {

    private Expression expression;

    private boolean asc = true;

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean b) {
        asc = b;
    }

    public void accept(OrderByVisitor orderByVisitor) {
        orderByVisitor.visit(this);
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public String toString() {
        return "" + expression + ((asc) ? "" : " DESC");
    }
}
