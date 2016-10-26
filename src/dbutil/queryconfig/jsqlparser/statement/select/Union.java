package dbutil.queryconfig.jsqlparser.statement.select;

import java.util.List;

/**
 * A UNION statement
 */
public class Union implements SelectBody {

    private List<PlainSelect> plainSelects;

    private List<OrderByElement> orderByElements;

    private Limit limit;

    private boolean distinct;

    private boolean all;

    public void accept(SelectVisitor selectVisitor) {
        selectVisitor.visit(this);
    }

    public List<OrderByElement> getOrderByElements() {
        return orderByElements;
    }

    /**
	 * the list of {@link PlainSelect}s in this UNION
	 * @return the list of {@link PlainSelect}s
	 */
    public List<PlainSelect> getPlainSelects() {
        return plainSelects;
    }

    public void setOrderByElements(List<OrderByElement> orderByElements) {
        this.orderByElements = orderByElements;
    }

    public void setPlainSelects(List<PlainSelect> list) {
        plainSelects = list;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    /**
	 * This is not 100% right; every UNION should have their own All/Distinct clause...
	 */
    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    /**
	 * This is not 100% right; every UNION should have their own All/Distinct clause...
	 */
    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public String toString() {
        StringBuilder selects = new StringBuilder(256);
        String allDistinct="";
        if (isAll()) {
            allDistinct = "ALL ";
        } else if (isDistinct()) {
            allDistinct = "DISTINCT ";
        }
        for (int i = 0; i < plainSelects.size(); i++) {
        	if(i>0){
        		selects.append(" UNION ").append(allDistinct);
        	}
    		selects.append(plainSelects.get(i)).append('\n');
        }
        PlainSelect.getFormatedList(selects,orderByElements, "ORDER BY",false);
        if(limit != null){
        	selects.append(limit.toString());
        }
        return selects.toString();
    }
}
