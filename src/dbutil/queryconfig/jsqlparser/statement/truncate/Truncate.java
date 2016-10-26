package dbutil.queryconfig.jsqlparser.statement.truncate;

import dbutil.queryconfig.jsqlparser.schema.Table;
import dbutil.queryconfig.jsqlparser.statement.Statement;
import dbutil.queryconfig.jsqlparser.statement.StatementVisitor;

/**
 * A TRUNCATE TABLE statement
 */
public class Truncate implements Statement {

    private Table table;

    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String toString() {
        return "TRUNCATE TABLE " + table;
    }
}
