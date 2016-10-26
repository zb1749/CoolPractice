package dbutil.queryconfig.jsqlparser.statement.select;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.parser.Token;
import dbutil.queryconfig.jsqlparser.schema.Table;
import dbutil.tools.StringUtils;

import java.util.Iterator;
import java.util.List;


/**
 * The core of a "SELECT" statement (no UNION, no ORDER BY) 
 */
public class PlainSelect implements SelectBody {

    protected Distinct distinct = null;

    protected List<SelectItem> selectItems;

    protected Table into;

    protected FromItem fromItem;

    protected List<Join> joins;

    protected Expression where;

    protected List<Expression> groupByColumnReferences;

    protected List<OrderByElement> orderByElements;

    protected Expression having;

    protected Limit limit;

    protected Top top;

	protected StartWithExpression startWith;
    private String hint;
    
    public Expression getStartWith() {
		return startWith;
	}
    public void setHint(Token t){
    	if(t!=null && t.specialToken!=null){
    		this.hint=t.specialToken.image;	
    	}
    }
	public void setStartWith(StartWithExpression startWith) {
		this.startWith = startWith;
	}

    /**
	 * The {@link FromItem} in this query
	 * @return the {@link FromItem}
	 */
    public FromItem getFromItem() {
        return fromItem;
    }

    public Table getInto() {
        return into;
    }

    /**
	 * The {@link SelectItem}s in this query (for example the A,B,C in "SELECT A,B,C")
	 * @return a list of {@link SelectItem}s
	 */
    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public Expression getWhere() {
        return where;
    }

    public void setFromItem(FromItem item) {
        fromItem = item;
    }

    public void setInto(Table table) {
        into = table;
    }

    public void setSelectItems(List<SelectItem> list) {
        selectItems = list;
    }

    public void setWhere(Expression where) {
        this.where = where;
    }

    /**
	 * The list of {@link Join}s
	 * @return the list of {@link Join}s
	 */
    public List<Join> getJoins() {
        return joins;
    }

    public void setJoins(List<Join> list) {
        joins = list;
    }

    public void accept(SelectVisitor selectVisitor) {
        selectVisitor.visit(this);
    }

    public List<OrderByElement> getOrderByElements() {
        return orderByElements;
    }

    public void setOrderByElements(List<OrderByElement> orderByElements) {
        this.orderByElements = orderByElements;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    public Top getTop() {
        return top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public Distinct getDistinct() {
        return distinct;
    }

    public void setDistinct(Distinct distinct) {
        this.distinct = distinct;
    }

    public Expression getHaving() {
        return having;
    }

    public void setHaving(Expression expression) {
        having = expression;
    }

    /**
	 * A list of {@link Expression}s of the GROUP BY clause.
	 * It is null in case there is no GROUP BY clause
	 * @return a list of {@link Expression}s 
	 */
    public List<Expression> getGroupByColumnReferences() {
        return groupByColumnReferences;
    }

    public void setGroupByColumnReferences(List<Expression> list) {
        groupByColumnReferences = list;
    }

    public String toString() {
    	StringBuilder sb=new StringBuilder(256);
    	sb.append("select ");
    	if(hint!=null){
    		sb.append(hint).append(' ');
    	}
    	if(distinct!=null){
    		sb.append(distinct.toString()).append(' ');
    	}
        if(top != null){
        	sb.append(top.toString()).append(' ');
        }
    	getStringList(sb,selectItems, ",", false);
        sb.append(" from ").append(fromItem.toString());
        if (joins != null) {
            Iterator<Join> it = joins.iterator();
            while (it.hasNext()) {
                Join join = (Join) it.next();
                if (join.isSimple()) {
                	sb.append(", ").append(join.toString());
                } else {
                	sb.append(" ").append(join.toString());
                }
            }
        }
        if((where != null)){
        	sb.append(" where ").append(where.toString());
        }
        if(startWith!=null){
        	sb.append(" START WITH ").append(startWith.toString());
        }
        
        getFormatedList(sb,groupByColumnReferences, "group by",false);
        if(having!=null){
        	sb.append(" having ").append(having);
        }
        getFormatedList(sb,orderByElements, "order by",false);
        if(limit != null){
        	sb.append(' ').append(limit.toString());
        }
        return sb.toString();
    }

    public static void getFormatedList(StringBuilder sb,List<?> list, String expression,boolean useBrackets) {
    	if(list==null || list.isEmpty())return;
    	sb.append(' ');
    	if(expression!=null){
    		sb.append(expression).append(' ');
    	}
    	getStringList(sb, list, ",", useBrackets);
    }
    
    public static void getStringList(StringBuilder sb,List<?> list, String comma, boolean useBrackets) {
        if (list != null) {
            if (useBrackets) {
                sb.append('(');
                StringUtils.joinTo(list, comma, sb);
                sb.append(')');
            }else{
            	StringUtils.joinTo(list, comma, sb);
            }
        }
    }
}
