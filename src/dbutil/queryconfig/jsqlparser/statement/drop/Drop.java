package dbutil.queryconfig.jsqlparser.statement.drop;

import java.util.List;

import dbutil.queryconfig.jsqlparser.statement.Statement;
import dbutil.queryconfig.jsqlparser.statement.StatementVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;

public class Drop implements Statement {

    private String type;

    private String name;

    private List<String> parameters;

    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public String getType() {
        return type;
    }

    public void setName(String string) {
        name = string;
    }

    public void setParameters(List<String> list) {
        parameters = list;
    }

    public void setType(String string) {
        type = string;
    }

    public String toString() {
    	StringBuilder sb=new StringBuilder(128);
    	sb.append("DROP " ).append(type).append(' ').append(name);
        if (parameters != null && parameters.size() > 0) {
        	sb.append(' ');
            PlainSelect.getStringList(sb,parameters,",",false);
        }
        return sb.toString();
    }
}
