package dbutil.queryconfig.jsqlparser.statement.update;

import java.util.List;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.parser.Token;
import dbutil.queryconfig.jsqlparser.schema.Column;
import dbutil.queryconfig.jsqlparser.statement.Statement;
import dbutil.queryconfig.jsqlparser.statement.StatementVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.FromItem;


/**
 * The update statement.
 */
public class Update implements Statement {
    /**
     * 其實只有兩種可能JPQL和table兩種可能
     * @return
     */
    private FromItem table;

    private Expression where;

    private List<Column> columns;

    private List<Expression> expressions;

    private String hint;
    public void setHint(Token t){
    	if(t!=null && t.specialToken!=null){
    		this.hint=t.specialToken.image;	
    	}
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

    /**
	 * The {@link Column}s in this update (as col1 and col2 in UPDATE col1='a', col2='b')
	 * @return a list of {@link Column}s
	 */
    public List<Column> getColumns() {
        return columns;
    }

    /**
	 * The {@link Expression}s in this update (as 'a' and 'b' in UPDATE col1='a', col2='b')
	 * @return a list of {@link Expression}s
	 */
    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setColumns(List<Column> list) {
        columns = list;
    }

    public void setExpressions(List<Expression> list) {
        expressions = list;
    }
    
    public String toString() {
        StringBuilder sql = new StringBuilder("update ");
        if(hint!=null){
        	sql.append(hint).append(' ');
        }
        sql.append(table).append(" set ");
        int i=0;
        for(Column col: columns){
        	if(i>0){
        		sql.append(',');
        	}
        	sql.append(col).append(" = ").append(expressions.get(i++));
        }
        if(where!=null)
        	sql.append(" where ").append(where);
        return sql.toString();
    }
}
