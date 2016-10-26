package dbutil.queryconfig.jsqlparser.statement.select;

public interface SelectVisitor {

    public void visit(PlainSelect plainSelect);

    public void visit(Union union);
}
