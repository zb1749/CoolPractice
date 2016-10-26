package dbutil.queryconfig.jsqlparser.statement.select;

/**
 * An item in a "SELECT [...] FROM item1" statement.
 * (for example a table or a sub-select) 
 */
public interface FromItem {

    public void accept(FromItemVisitor fromItemVisitor);

    public String getAlias();

    public void setAlias(String alias);
   //返回表明定義不含Alias的部分 
    public String getWholeTableName();
}
