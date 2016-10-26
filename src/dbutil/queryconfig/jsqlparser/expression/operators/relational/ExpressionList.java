package dbutil.queryconfig.jsqlparser.expression.operators.relational;

import java.util.Arrays;
import java.util.List;

import dbutil.queryconfig.jsqlparser.expression.Child;
import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.Function;
import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;


/**
 * A list of expressions, as in SELECT A FROM TAB WHERE B IN (expr1,expr2,expr3)
 */
public class ExpressionList implements ItemsList {

	private Function parent;
    private List<Expression> expressions;
    private String between=",";
    
    public Function getParent() {
		return parent;
	}

	public ExpressionList setParent(Function parent) {
		this.parent = parent;
		return this;
	}

	public ExpressionList() {
    }

    public String getBetween() {
		return between;
	}

	public ExpressionList setBetween(String between) {
		this.between = between;
		return this;
	}

	public int size(){
    	return expressions.size();
    }
    
    public boolean isEmpty(){
    	return expressions==null?true:expressions.isEmpty();
    }
    
    public ExpressionList(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public ExpressionList(Expression... expression) {
        this.expressions = Arrays.asList(expression);
	}

	public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> list) {
        expressions = list;
        if(list!=null){
        	for(Expression e:list){
            	if(e instanceof Child){
            		((Child) e).setParent(this);
            	}
            }	
        }
    }

    public void accept(ItemsListVisitor itemsListVisitor) {
        itemsListVisitor.visit(this);
    }

    public String toString() {
    	StringBuilder sb=new StringBuilder();
        PlainSelect.getStringList(sb,expressions, between, true);
        return sb.toString();
    }
}
