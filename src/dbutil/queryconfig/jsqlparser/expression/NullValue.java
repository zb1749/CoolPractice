package dbutil.queryconfig.jsqlparser.expression;

/**
 *  A "NULL" in a sql statement
 */
public class NullValue implements Expression {

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public String toString() {
        return "NULL";
    }
}
