package dbutil.queryconfig.jsqlparser.expression;

/**
 * A '?' in a statement
 */
public class JdbcParameter implements Expression {

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public String toString() {
        return "?";
    }
}
