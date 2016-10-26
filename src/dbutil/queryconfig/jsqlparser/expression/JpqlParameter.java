package dbutil.queryconfig.jsqlparser.expression;

import dbutil.queryconfig.jsqlparser.statement.select.FromItem;
import dbutil.queryconfig.jsqlparser.statement.select.FromItemVisitor;
import dbutil.tools.StringUtils;


/**
 * A '?' in a statement
 */
public class JpqlParameter implements Expression, FromItem {
    private String name;
    private int index = -1;
    private JpqlDataType type;
    private ThreadLocal<Object> resolved = new ThreadLocal<Object>(); //-1未解析 0已解析 >0数组参数，需要对应多次绑定

    public String getName() {
        return name;
    }

    public JpqlDataType getType() {
        return type;
    }

    public boolean isNamedParam() {
        return name != null;
    }

    public boolean isIndexParam() {
        return index > -1;
    }

    public int getIndex() {
        return index;
    }

    public Object getResolved() {
        return resolved.get();
    }

    public void setResolved(String text) {
        resolved.set(text);
    }

    public void setResolved(Integer resolved) {
        this.resolved.set(resolved);
    }

    public JpqlParameter(String str, boolean isNumber, String type) {
        if (isNumber) {
            this.index = Integer.valueOf(str);
        } else {
            this.name = str;
        }
        if (type != null) {
            this.type = JpqlDataType.valueOf(type.toUpperCase());
        }
    }

    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    public String toString() {
        String s = getReslovedString();
        if (StringUtils.isEmpty(tableAlias)) {
            return s;
        } else {
            return StringUtils.concat(s, " ", tableAlias);
        }
    }

    private String getReslovedString() {
        Object obj = resolved.get();
        if (obj != null && obj instanceof String) {
            return (String) obj;
        }
        Integer value = (Integer) obj;
        if (value == null || value < 0) {//未解析
            if (name == null) {
                return "?".concat(String.valueOf(index));
            } else {
                return ":".concat(name);
            }
        }
        if (value == 0) {//单参数
            return "?";
        } else {//value>0
            StringBuilder sb = new StringBuilder("?");
            StringUtils.repeat(sb, ",?", value - 1);
            return sb.toString();
        }
    }

    public Object getKey() {
        if (name != null) return name;
        return index;
    }

    public void accept(FromItemVisitor fromItemVisitor) {
        fromItemVisitor.visit(this);
    }

    private String tableAlias;

    public String getAlias() {
        return tableAlias;
    }

    public void setAlias(String alias) {
        this.tableAlias = alias;
    }

    public String getWholeTableName() {
        return getReslovedString();
    }
}