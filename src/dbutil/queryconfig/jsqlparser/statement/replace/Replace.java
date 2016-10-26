package dbutil.queryconfig.jsqlparser.statement.replace;

import java.util.List;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ItemsList;
import dbutil.queryconfig.jsqlparser.schema.Column;
import dbutil.queryconfig.jsqlparser.schema.Table;
import dbutil.queryconfig.jsqlparser.statement.Statement;
import dbutil.queryconfig.jsqlparser.statement.StatementVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;

/**
 * The replace statement.
 */
public class Replace implements Statement {

    private Table table;

    private List<Column> columns;

    private ItemsList itemsList;

    private List<Expression> expressions;

    private boolean useValues = true;

    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table name) {
        table = name;
    }

    /**
	 * A list of {@link Column}s either from a "REPLACE mytab (col1, col2) [...]" or a "REPLACE mytab SET col1=exp1, col2=exp2".
	 * @return a list of {@link Column}s
	 */
    public List<Column> getColumns() {
        return columns;
    }

    /**
	 * An {@link ItemsList} (either from a "REPLACE mytab VALUES (exp1,exp2)" or a "REPLACE mytab SELECT * FROM mytab2")  
	 * it is null in case of a "REPLACE mytab SET col1=exp1, col2=exp2"  
	 */
    public ItemsList getItemsList() {
        return itemsList;
    }

    public void setColumns(List<Column> list) {
        columns = list;
    }

    public void setItemsList(ItemsList list) {
        itemsList = list;
    }

    /**
	 * A list of {@link dbutil.queryconfig.jsqlparser.expression.Expression}s (from a "REPLACE mytab SET col1=exp1, col2=exp2"). <br>
	 * it is null in case of a "REPLACE mytab (col1, col2) [...]"  
	 */
    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> list) {
        expressions = list;
    }

    public boolean isUseValues() {
        return useValues;
    }

    public void setUseValues(boolean useValues) {
        this.useValues = useValues;
    }

    public String toString() {
        StringBuilder sb=new StringBuilder(256).append("REPLACE ").append(table.toString());
        if (expressions != null && columns != null) {
        	sb.append(" SET ");
            for (int i = 0, s = columns.size(); i < s; i++) {
            	sb.append(columns.get(i).toString()).append('=');
            	sb.append(expressions.get(i).toString());
            	if(i < s - 1){
            		sb.append(", ");
            	}
            }
        } else if (columns != null) {
        	sb.append(' ');
            PlainSelect.getStringList(sb,columns, ",", true);
        }
        if (itemsList != null) {
            if (useValues) {
            	sb.append(" VALUES");
            }
            sb.append(' ').append(itemsList.toString());
        }
        return sb.toString();
    }
}
