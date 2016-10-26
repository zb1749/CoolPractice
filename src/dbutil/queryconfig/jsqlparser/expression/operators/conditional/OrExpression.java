package dbutil.queryconfig.jsqlparser.expression.operators.conditional;

import dbutil.queryconfig.jsqlparser.expression.BinaryExpression;
import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.ExpressionVisitor;

public class OrExpression extends BinaryExpression {

    public OrExpression(Expression leftExpression, Expression rightExpression) {
        setLeftExpression(leftExpression);
        setRightExpression(rightExpression);
    }

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public String getStringExpression() {
        return "OR";
    }
}
