package dbutil.queryconfig.jsqlparser.util.deparser;

import java.util.Iterator;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.ExpressionVisitor;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ExpressionList;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ItemsListVisitor;
import dbutil.queryconfig.jsqlparser.schema.Column;
import dbutil.queryconfig.jsqlparser.statement.insert.Insert;
import dbutil.queryconfig.jsqlparser.statement.select.SelectVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.SubSelect;

/**
 * A class to de-parse (that is, tranform from JSqlParser hierarchy into a string)
 * an {@link dbutil.queryconfig.jsqlparser.statement.insert.Insert}
 */
public class InsertDeParser implements ItemsListVisitor {

    protected StringBuffer buffer;

    protected ExpressionVisitor expressionVisitor;

    protected SelectVisitor selectVisitor;

    public InsertDeParser() {
    }

    /**
	 * @param expressionVisitor a {@link ExpressionVisitor} to de-parse {@link dbutil.queryconfig.jsqlparser.expression.Expression}s. It has to share the same<br>
	 * StringBuffer (buffer parameter) as this object in order to work
	 * @param selectVisitor a {@link SelectVisitor} to de-parse {@link dbutil.queryconfig.jsqlparser.statement.select.Select}s.
	 * It has to share the same<br>
	 * StringBuffer (buffer parameter) as this object in order to work
	 * @param buffer the buffer that will be filled with the insert
	 */
    public InsertDeParser(ExpressionVisitor expressionVisitor, SelectVisitor selectVisitor, StringBuffer buffer) {
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

    public void deParse(Insert insert) {
        buffer.append("INSERT INTO ");
        buffer.append(insert.getTable().getWholeTableName());
        if (insert.getColumns() != null) {
            buffer.append("(");
            for (Iterator<Column> iter = insert.getColumns().iterator(); iter.hasNext(); ) {
                Column column = (Column) iter.next();
                buffer.append(column.getColumnName());
                if (iter.hasNext()) {
                    buffer.append(", ");
                }
            }
            buffer.append(")");
        }
        insert.getItemsList().accept(this);
    }

    public void visit(ExpressionList expressionList) {
        buffer.append(" VALUES (");
        for (Iterator<Expression> iter = expressionList.getExpressions().iterator(); iter.hasNext(); ) {
            Expression expression = (Expression) iter.next();
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
