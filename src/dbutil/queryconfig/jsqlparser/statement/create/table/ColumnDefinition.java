package dbutil.queryconfig.jsqlparser.statement.create.table;

import java.util.List;

import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;


/**
 * A column definition in a CREATE TABLE statement.<br>
 * Example: mycol VARCHAR(30) NOT NULL
 */
public class ColumnDefinition {

    private String columnName;

    private ColDataType colDataType;

    private List<String> columnSpecStrings;

    /**
	 * A list of strings of every word after the datatype of the column.<br>
	 * Example ("NOT", "NULL")
	 */
    public List<String> getColumnSpecStrings() {
        return columnSpecStrings;
    }

    public void setColumnSpecStrings(List<String> list) {
        columnSpecStrings = list;
    }

    /**
	 * The {@link ColDataType} of this column definition 
	 */
    public ColDataType getColDataType() {
        return colDataType;
    }

    public void setColDataType(ColDataType type) {
        colDataType = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String string) {
        columnName = string;
    }

    public String toString() {
    	StringBuilder sb=new StringBuilder(columnName);
    	sb.append(' ').append(colDataType).append(' ');
    	PlainSelect.getStringList(sb,columnSpecStrings, " ", false);
    	return sb.toString();
    }
}
