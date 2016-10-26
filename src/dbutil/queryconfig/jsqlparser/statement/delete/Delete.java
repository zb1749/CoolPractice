package dbutil.queryconfig.jsqlparser.statement.delete;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.parser.Token;
import dbutil.queryconfig.jsqlparser.statement.Statement;
import dbutil.queryconfig.jsqlparser.statement.StatementVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.FromItem;

public class Delete implements Statement {

    private FromItem table;

    private Expression where;
    private String alias;
    private String hint;
    
    public void setHint(Token t){
    	if(t!=null && t.specialToken!=null){
    		this.hint=t.specialToken.image;	
    	}
    }

    public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public FromItem getTable() {
        return table;
    }

    public Expression getWhere() {
        return where;
    }

    public void setTable(FromItem name) {
        table = name;
    }

    public void setWhere(Expression expression) {
        where = expression;
    }

    public String toString() {
    	StringBuilder sb=new StringBuilder(128);
    	sb.append("delete ");
    	if(hint!=null){
    		sb.append(hint).append(' ');
    	}
    	if(alias!=null){
    		sb.append(alias).append(' ');
    	}
    	sb.append("from ");
    	sb.append(table.toString());
    	if(where !=null){
    		sb.append(" where ");
    		sb.append(where);
    	}
        return sb.toString();
    }
}
