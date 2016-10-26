package dbutil.queryconfig.jsqlparser.expression;

public interface Expression {

    public void accept(ExpressionVisitor expressionVisitor);
}
