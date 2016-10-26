package dbutil.queryconfig.jsqlparser.statement;

/**
 * An operation on the db (SELECT, UPDATE ecc.) 
 */
public interface Statement {

    public void accept(StatementVisitor statementVisitor);
}
