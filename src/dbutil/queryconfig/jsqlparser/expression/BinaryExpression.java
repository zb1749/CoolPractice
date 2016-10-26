package dbutil.queryconfig.jsqlparser.expression;

import dbutil.queryconfig.jsqlparser.expression.operators.relational.InExpression;


/**
 * A basic class for binary expressions, that is expressions having a left member and a right member
 * which are in turn expressions. 
 */
public abstract class BinaryExpression implements Expression {
	public enum Prior{
		LEFT,RIGHT
	}
	
    public Prior getPrior() {
		return prior;
	}

	public void setPrior(Prior prior) {
		this.prior = prior;
	}

	private Expression leftExpression;

    private Expression rightExpression;

    private boolean not = false;
    
    private Prior prior; 
    
    //变量绑定值是否为空
    private ThreadLocal<Boolean> isEmpty = new ThreadLocal<Boolean>(); 

    public boolean isEmpty() {
		return isEmpty.get()==null?false:isEmpty.get();
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty.set(isEmpty);
	}

	public BinaryExpression() {
		setEmpty(false);
    }

    public Expression getLeftExpression() {
        return leftExpression;
    }

    public Expression getRightExpression() {
        return rightExpression;
    }

    public void setLeftExpression(Expression expression) {
        leftExpression = expression;
    }

    public void setRightExpression(Expression expression) {
        rightExpression = expression;
    }

    public void setNot() {
        not = true;
    }

    public boolean isNot() {
        return not;
    }

    public String toString() {
    	Expression  _leftExpression = getLeftExpression();
    	if(isEmptyExpress(_leftExpression)){
    		_leftExpression=null;
    	}
    	Expression  _rightExpression = getRightExpression();
    	if(isEmptyExpress(_rightExpression)){
    		_rightExpression=null;
    	}
    	
    	if(_leftExpression==null && _rightExpression==null){
    		return "";
    	}
    	String left=_leftExpression==null?"":_leftExpression.toString();
    	String right=_rightExpression==null?"":_rightExpression.toString();
    	StringBuilder sb=new StringBuilder(left.length()+right.length()+16);
    	if(not){
    		sb.append("NOT ");
    	}
    	if(prior==Prior.LEFT){
    		sb.append("PRIOR ");
    	}
    	if(_leftExpression!=null){
    		sb.append(left).append(' ');	
    	}
    	if(_leftExpression!=null && _rightExpression!=null){
    		sb.append(getStringExpression());
    		if(prior==Prior.RIGHT){
        		sb.append(" PRIOR");
        	}
    	}
    	
    	if(rightExpression!=null){
    		sb.append(' ').append(right);
    	}
    	return sb.toString(); 
    }

    static boolean isEmptyExpress(Expression e) {
		if(e instanceof Parenthesis){
			e=((Parenthesis) e).getExpression();
		}
		if(e instanceof BinaryExpression){
			return (((BinaryExpression) e).isEmpty());
		}else if(e instanceof InExpression){
			return ((InExpression)e).isEmpty();
		}
		return false;
	}

	public abstract String getStringExpression();
}
