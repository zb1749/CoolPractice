package dbutil.queryconfig.jsqlparser.statement.select;

public interface SelectBody {

    public void accept(SelectVisitor selectVisitor);
}
