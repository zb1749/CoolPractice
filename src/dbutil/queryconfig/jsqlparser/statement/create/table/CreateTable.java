package dbutil.queryconfig.jsqlparser.statement.create.table;

import java.util.List;

import dbutil.queryconfig.jsqlparser.schema.Table;
import dbutil.queryconfig.jsqlparser.statement.Statement;
import dbutil.queryconfig.jsqlparser.statement.StatementVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;


/**
 * A "CREATE TABLE" statement
 */
public class CreateTable implements Statement {

    private Table table;

    private List<String> tableOptionsStrings;

    private List<ColumnDefinition> columnDefinitions;

    private List<Index> indexes;

    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    /**
     * The name of the table to be created
     */
    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * A list of {@link ColumnDefinition}s of this table.
     */
    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    public void setColumnDefinitions(List<ColumnDefinition> list) {
        columnDefinitions = list;
    }

    /**
     * A list of options (as simple strings) of this table definition, as ("TYPE", "=", "MYISAM") 
     */
    public List<String> getTableOptionsStrings() {
        return tableOptionsStrings;
    }

    public void setTableOptionsStrings(List<String> list) {
        tableOptionsStrings = list;
    }

    /**
     * A list of {@link Index}es (for example "PRIMARY KEY") of this table.<br>
     * Indexes created with column definitions (as in mycol INT PRIMARY KEY) are not inserted into this list.  
     */
    public List<Index> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Index> list) {
        indexes = list;
    }

    public String toString() {
        StringBuilder sb=new StringBuilder().append("CREATE TABLE ");
        sb.append(table).append(" (");
        PlainSelect.getStringList(sb,columnDefinitions, ",", false);
        if (indexes != null && indexes.size() > 0) {
        	sb.append(", ");
            PlainSelect.getStringList(sb,indexes,",",false);
        }
        sb.append(") ");
        PlainSelect.getStringList(sb, tableOptionsStrings, " ", false);
        return sb.toString();
    }
}
