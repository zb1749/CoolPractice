package dbutil.queryconfig.jsqlparser.statement.select;

public interface SelectItemVisitor {

    public void visit(AllColumns allColumns);

    public void visit(AllTableColumns allTableColumns);

    public void visit(SelectExpressionItem selectExpressionItem);
}
