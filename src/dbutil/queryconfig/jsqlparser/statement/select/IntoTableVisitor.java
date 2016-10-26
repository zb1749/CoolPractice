package dbutil.queryconfig.jsqlparser.statement.select;

import dbutil.queryconfig.jsqlparser.schema.Table;

public interface IntoTableVisitor {

    public void visit(Table tableName);
}
