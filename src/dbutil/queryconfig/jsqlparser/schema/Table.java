package dbutil.queryconfig.jsqlparser.schema;

import dbutil.queryconfig.jsqlparser.statement.select.FromItem;
import dbutil.queryconfig.jsqlparser.statement.select.FromItemVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.IntoTableVisitor;
import dbutil.tools.StringUtils;


/**
 * A table. It can have an alias and the schema name it belongs to. 
 */
public class Table implements FromItem {

    private String schemaName;

    private String name;

    private String alias;

    public Table() {
    }

    public Table(String schemaName, String name) {
        this.schemaName = schemaName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSchemaName() {
        return schemaName;
    }
    
    public void addDbLink(String dbLink){
    	if(dbLink!=null)
    		name=StringUtils.concat(name,"@",dbLink);
    }
    
    public void setName(String string) {
        name = string;
    }

    public void setSchemaName(String string) {
        schemaName = string;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String string) {
        alias = string;
    }

    public String getWholeTableName() {
        String tableWholeName = null;
        if (name == null) {
            return null;
        }
        if (schemaName != null) {
            tableWholeName = schemaName + "." + name;
        } else {
            tableWholeName = name;
        }
        return tableWholeName;
    }

    public void accept(FromItemVisitor fromItemVisitor) {
        fromItemVisitor.visit(this);
    }

    public void accept(IntoTableVisitor intoTableVisitor) {
        intoTableVisitor.visit(this);
    }
    /*
     * Jiyi modified at 2011-6-2
     * The oracle does't not support 'AS' in table alias, since it is a keyword keyword.remove the key.
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	StringBuilder sb=new StringBuilder();
    	sb.append(getWholeTableName());
    	if(alias!=null){
    		sb.append(' ').append(alias);
    	}
        return sb.toString();
    }
}
