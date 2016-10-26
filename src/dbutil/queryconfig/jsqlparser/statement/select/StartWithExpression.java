package dbutil.queryconfig.jsqlparser.statement.select;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.ExpressionVisitor;

public class StartWithExpression implements Expression{
	private Expression startExpression;
	private Expression connectExpression;
	
	public StartWithExpression(Expression stExpression,Expression connectBy){
		this.startExpression=stExpression;
		this.connectExpression=connectBy;
	}
	
	public void accept(ExpressionVisitor expressionVisitor) {
		if(startExpression!=null)startExpression.accept(expressionVisitor);
		if(connectExpression!=null)connectExpression.accept(expressionVisitor);
	}

	@Override
	public String toString() {
		String s1=startExpression!=null?startExpression.toString():"";
		String s2=connectExpression!=null?connectExpression.toString():"";
		StringBuilder sb=new StringBuilder(s1.length()+s2.length()+16);
		sb.append(s1).append(" ");
		if(connectExpression!=null){
			sb.append("CONNECT BY ").append(s2);
		}
		return sb.toString();
	}

}
