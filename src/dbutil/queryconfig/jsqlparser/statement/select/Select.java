package dbutil.queryconfig.jsqlparser.statement.select;

import java.util.Iterator;
import java.util.List;

import dbutil.queryconfig.jsqlparser.statement.Statement;
import dbutil.queryconfig.jsqlparser.statement.StatementVisitor;

public class Select implements Statement {

    private SelectBody selectBody;

    private List<WithItem> withItemsList;

    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public SelectBody getSelectBody() {
        return selectBody;
    }

    public void setSelectBody(SelectBody body) {
        selectBody = body;
    }

    public String toString() {
        StringBuffer retval = new StringBuffer();
        if (withItemsList != null && !withItemsList.isEmpty()) {
            retval.append("WITH ");
            for (Iterator<WithItem> iter = withItemsList.iterator(); iter.hasNext(); ) {
                WithItem withItem = iter.next();
                retval.append(withItem);
                if (iter.hasNext()) retval.append(",");
                retval.append(" ");
            }
        }
        retval.append(selectBody);
        return retval.toString();
    }

    public List<WithItem> getWithItemsList() {
        return withItemsList;
    }

    public void setWithItemsList(List<WithItem> withItemsList) {
        this.withItemsList = withItemsList;
    }
}
