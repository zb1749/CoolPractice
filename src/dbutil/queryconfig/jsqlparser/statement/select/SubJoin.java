package dbutil.queryconfig.jsqlparser.statement.select;

/**
 * A table created by "(tab1 join tab2)".
 */
public class SubJoin implements FromItem {

    private FromItem left;

    private Join join;

    private String alias;

    public void accept(FromItemVisitor fromItemVisitor) {
        fromItemVisitor.visit(this);
    }

    public FromItem getLeft() {
        return left;
    }

    public void setLeft(FromItem l) {
        left = l;
    }

    public Join getJoin() {
        return join;
    }

    public void setJoin(Join j) {
        join = j;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String string) {
        alias = string;
    }

    public String toString() {
        return "(" + left + " " + join + ")" + ((alias != null) ? " ".concat(alias): "");
    }

	public String getWholeTableName() {
		return "(" + left + " " + join + ")";
	}
}
