package dbutil.queryconfig.jsqlparser.expression.operators.relational;

import dbutil.queryconfig.jsqlparser.expression.BinaryExpression;
import dbutil.queryconfig.jsqlparser.expression.ExpressionVisitor;

public class MinorThan extends BinaryExpression {

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public String getStringExpression() {
        return "<";
    }
}
