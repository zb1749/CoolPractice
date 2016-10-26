package dbutil.queryconfig.jsqlparser.expression.operators.relational;

import dbutil.queryconfig.jsqlparser.statement.select.SubSelect;

public interface ItemsListVisitor {

    public void visit(SubSelect subSelect);

    public void visit(ExpressionList expressionList);
}
