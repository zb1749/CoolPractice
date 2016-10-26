package dbutil.queryconfig.jsqlparser.statement.select;

import dbutil.queryconfig.jsqlparser.expression.JpqlParameter;
import dbutil.queryconfig.jsqlparser.schema.Table;

public interface FromItemVisitor {

    public void visit(Table tableName);

    public void visit(SubSelect subSelect);

    public void visit(SubJoin subjoin);
    
    public void visit(JpqlParameter tableClip);
}
