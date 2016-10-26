package dbutil.queryconfig.jsqlparser.util.deparser;

import java.util.Iterator;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.ExpressionVisitor;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ExpressionList;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ItemsListVisitor;
import dbutil.queryconfig.jsqlparser.schema.Column;
import dbutil.queryconfig.jsqlparser.statement.replace.Replace;
import dbutil.queryconfig.jsqlparser.statement.select.SelectVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.SubSelect;

/**
 * A class to de-parse (that is, tranform from JSqlParser hierarchy into a string)
 * a {@link dbutil.queryconfig.jsqlparser.statement.replace.Replace}
 */
public class ReplaceDeParser implements ItemsListVisitor {

    protected StringBuffer buffer;

    protected ExpressionVisitor expressionVisitor;

    protected SelectVisitor selectVisitor;

    public ReplaceDeParser() {
    }

    /**
	 * @param expressionVisitor a {@link ExpressionVisitor} to de-parse expressions. It has to share the same<br>
	 * StringBuffer (buffer parameter) as this object in order to work
	 * @param selectVisitor a {@link SelectVisitor} to de-parse {@link dbutil.queryconfig.jsqlparser.statement.select.Select}s.
	 * It has to share the same<br>
	 * StringBuffer (buffer parameter) as this object in order to work
	 * @param buffer the buffer that will be filled with the select
	 */
    public ReplaceDeParser(ExpressionVisitor expressionVisitor, SelectVisitor selectVisitor, StringBuffer buffer) {
        this.buffer = buffer;
        this.expressionVisitor = expressionVisitor;
        this.selectVisitor = selectVisitor;
    }

    public StringBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(StringBuffer buffer) {
        this.buffer = buffer;
    }

    public void deParse(Replace replace) {
        buffer.append("REPLACE " + replace.getTable().getWholeTableName());
        if (replace.getItemsList() != null) {
            if (replace.getColumns() != null) {
                buffer.append(" (");
                for (int i = 0; i < replace.getColumns().size(); i++) {
                    Column column = (Column) replace.getColumns().get(i);
                    buffer.append(column.getWholeColumnName());
                    if (i < replace.getColumns().size() - 1) {
                        buffer.append(", ");
                    }
                }
                buffer.append(") ");
            } else {
                buffer.append(" ");
            }
        } else {
            buffer.append(" SET ");
            for (int i = 0; i < replace.getColumns().size(); i++) {
                Column column = (Column) replace.getColumns().get(i);
                buffer.append(column.getWholeColumnName() + "=");
                Expression expression = (Expression) replace.getExpressions().get(i);
                expression.accept(expressionVisitor);
                if (i < replace.getColumns().size() - 1) {
                    buffer.append(", ");
                }
            }
        }
    }

    public void visit(ExpressionList expressionList) {
        buffer.append(" VALUES (");
        for (Iterator<Expression> iter = expressionList.getExpressions().iterator(); iter.hasNext(); ) {
            Expression expression = iter.next();
            expression.accept(expressionVisitor);
            if (iter.hasNext()) buffer.append(", ");
        }
        buffer.append(")");
    }

    public void visit(SubSelect subSelect) {
        subSelect.getSelectBody().accept(selectVisitor);
    }

    public ExpressionVisitor getExpressionVisitor() {
        return expressionVisitor;
    }

    public SelectVisitor getSelectVisitor() {
        return selectVisitor;
    }

    public void setExpressionVisitor(ExpressionVisitor visitor) {
        expressionVisitor = visitor;
    }

    public void setSelectVisitor(SelectVisitor visitor) {
        selectVisitor = visitor;
    }
}
