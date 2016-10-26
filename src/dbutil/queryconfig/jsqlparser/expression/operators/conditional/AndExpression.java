package dbutil.queryconfig.jsqlparser.expression.operators.conditional;

import dbutil.queryconfig.jsqlparser.expression.BinaryExpression;
import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.ExpressionVisitor;

public class AndExpression extends BinaryExpression {

    public AndExpression(Expression leftExpression, Expression rightExpression) {
        setLeftExpression(leftExpression);
        setRightExpression(rightExpression);
    }

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public String getStringExpression() {
        return "AND";
    }
}
