package dbutil.queryconfig.jsqlparser.schema;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.ExpressionVisitor;
import dbutil.queryconfig.jsqlparser.expression.Function;

/**
 * A column. It can have the table name it belongs to. 
 */
public class Column implements Expression {

    private String columnName = "";

    private Table table;

    private String alias;
    private String schema;

    public Column() {
    }

    public Column(Table table, String columnName) {
        this.table = table;
        this.columnName = columnName;
    }

    public Column(String alias, String columnName) {
        this.alias=alias;
        this.columnName = columnName;
    }
    
    public Column(String schema,String name, String columnName) {
    	this.schema=schema;
    	this.alias=name;
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public Table getTable() {
        return table;
    }

    public void setColumnName(String string) {
        columnName = string;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setSchema(String schema) {
		this.schema = schema;
	}

    public String getSchema() {
		return schema;
	}

    public void setTableAlias(String table) {
        this.alias = table;
    }

    public String getTableAlias() {
        return alias;
    }

	/**
	 * @return the name of the column, prefixed with 'tableName' and '.' 
	 */
    public String getWholeColumnName() {
        String columnWholeName = null;
        String tableWholeName = table.getWholeTableName();
        if (tableWholeName != null && tableWholeName.length() != 0) {
            columnWholeName = tableWholeName + "." + columnName;
        } else {
            columnWholeName = columnName;
        }
        return columnWholeName;
    }

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public String toString() {
        return getWholeColumnName();
    }
    
	public static Expression getExpression(String columnName) {
		if (columnName.equals("current_timestamp") || columnName.equals("current_date")
				|| columnName.equals("current_time")) {
			return new Function(columnName);
		}
		return new Column("", columnName);
	}
}
