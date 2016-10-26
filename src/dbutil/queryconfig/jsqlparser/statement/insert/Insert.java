package dbutil.queryconfig.jsqlparser.statement.insert;

import java.util.List;

import dbutil.queryconfig.jsqlparser.expression.operators.relational.ItemsList;
import dbutil.queryconfig.jsqlparser.parser.Token;
import dbutil.queryconfig.jsqlparser.schema.Column;
import dbutil.queryconfig.jsqlparser.statement.Statement;
import dbutil.queryconfig.jsqlparser.statement.StatementVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.FromItem;
import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;


/**
 * The insert statement.
 * Every column name in <code>columnNames</code> matches an item in <code>itemsList</code>
 */
public class Insert implements Statement {

    private FromItem table;

    private List<Column> columns;

    private ItemsList itemsList;

    private boolean useValues = true;

    private String hint;
    
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }
    
    public void setHint(Token t){
    	if(t!=null && t.specialToken!=null){
    		this.hint=t.specialToken.image;	
    	}
    }

    public FromItem getTable() {
        return table;
    }

    public void setTable(FromItem name) {
        table = name;
    }

    /**
	 * Get the columns (found in "INSERT INTO (col1,col2..) [...]" )
	 * @return a list of {@link Column}
	 */
    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> list) {
        columns = list;
    }

    /**
	 * Get the values (as VALUES (...) or SELECT) 
	 * @return the values of the insert
	 */
    public ItemsList getItemsList() {
        return itemsList;
    }

    public void setItemsList(ItemsList list) {
        itemsList = list;
    }

    public boolean isUseValues() {
        return useValues;
    }

    public void setUseValues(boolean useValues) {
        this.useValues = useValues;
    }

    public String toString() {
    	StringBuilder sb=new StringBuilder(128);
        sb.append("insert ");
        if(hint!=null){
        	sb.append(hint).append(' ');
        }
        sb.append("into ");
        sb.append(table).append(' ');
        if(columns != null){
        	PlainSelect.getStringList(sb,columns, ",", true);
        	sb.append(' ');
        }
        if (useValues) {
        	sb.append("values ").append(itemsList);
        } else {
        	sb.append(itemsList);
        }
        return sb.toString();
    }
}
