package dbutil.queryconfig.jsqlparser.expression;

import java.util.List;

import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;

/**
 * CASE/WHEN expression.
 * 
 * Syntax:
 * <code><pre>
 * CASE 
 * WHEN condition THEN expression
 * [WHEN condition THEN expression]...
 * [ELSE expression]
 * END
 * </pre></code>
 * 
 * <br/>
 * or <br/>
 * <br/>
 * 
 * <code><pre>
 * CASE expression 
 * WHEN condition THEN expression
 * [WHEN condition THEN expression]...
 * [ELSE expression]
 * END
 * </pre></code>
 *  
 *  See also:
 *  https://aurora.vcu.edu/db2help/db2s0/frame3.htm#casexp
 *  http://sybooks.sybase.com/onlinebooks/group-as/asg1251e/commands/@ebt-link;pt=5954?target=%25N%15_52628_START_RESTART_N%25
 *  
 *  
 * @author Havard Rast Blok
 */
public class CaseExpression implements Expression {

    private Expression switchExpression;

    private List<Expression> whenClauses;

    private Expression elseExpression;

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    /**
	 * @return Returns the switchExpression.
	 */
    public Expression getSwitchExpression() {
        return switchExpression;
    }

    /**
	 * @param switchExpression The switchExpression to set.
	 */
    public void setSwitchExpression(Expression switchExpression) {
        this.switchExpression = switchExpression;
    }

    /**
	 * @return Returns the elseExpression.
	 */
    public Expression getElseExpression() {
        return elseExpression;
    }

    /**
	 * @param elseExpression The elseExpression to set.
	 */
    public void setElseExpression(Expression elseExpression) {
        this.elseExpression = elseExpression;
    }

    /**
	 * @return Returns the whenClauses.
	 */
    public List<Expression> getWhenClauses() {
        return whenClauses;
    }

    /**
	 * @param whenClauses The whenClauses to set.
	 */
    public void setWhenClauses(List<Expression> whenClauses) {
        this.whenClauses = whenClauses;
    }

    public String toString() {
    	StringBuilder sb=new StringBuilder(128);
    	sb.append("CASE ");
    	if(switchExpression != null){
    		sb.append(switchExpression.toString()).append(' ');
    	}
    	PlainSelect.getStringList(sb,whenClauses, " ", false);
    	sb.append(' ');
    	if(elseExpression != null) {
    		sb.append("ELSE ").append(elseExpression.toString()).append(' ');
    	}
    	sb.append("END");
        return sb.toString();
    }
}
