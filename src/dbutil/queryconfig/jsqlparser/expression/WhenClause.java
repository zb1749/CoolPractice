package dbutil.queryconfig.jsqlparser.expression;

/**
 * A clause of following syntax: 
 * WHEN condition THEN expression.
 * Which is part of a CaseExpression.
 * 
 * @author Havard Rast Blok
 */
public class WhenClause implements Expression {

    private Expression whenExpression;

    private Expression thenExpression;

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    /**
	 * @return Returns the thenExpression.
	 */
    public Expression getThenExpression() {
        return thenExpression;
    }

    /**
	 * @param thenExpression The thenExpression to set.
	 */
    public void setThenExpression(Expression thenExpression) {
        this.thenExpression = thenExpression;
    }

    /**
	 * @return Returns the whenExpression.
	 */
    public Expression getWhenExpression() {
        return whenExpression;
    }

    /**
	 * @param whenExpression The whenExpression to set.
	 */
    public void setWhenExpression(Expression whenExpression) {
        this.whenExpression = whenExpression;
    }

    public String toString() {
        return "WHEN " + whenExpression + " THEN " + thenExpression;
    }
}
