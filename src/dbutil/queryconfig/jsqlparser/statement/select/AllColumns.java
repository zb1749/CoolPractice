package dbutil.queryconfig.jsqlparser.statement.select;

/**
 * All the columns (as in "SELECT * FROM ...")
 */
public class AllColumns implements SelectItem {

    public void accept(SelectItemVisitor selectItemVisitor) {
        selectItemVisitor.visit(this);
    }

    public String toString() {
        return "*";
    }
}
