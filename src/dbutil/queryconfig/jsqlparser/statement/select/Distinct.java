package dbutil.queryconfig.jsqlparser.statement.select;

import java.util.List;

/**
 * A DISTINCT [ON (expression, ...)] clause
 */
public class Distinct {

    private List<SelectItem> onSelectItems;

    /**
	 * A list of {@link SelectItem}s expressions, as in "select DISTINCT ON (a,b,c) a,b FROM..." 
	 * @return a list of {@link SelectItem}s expressions
	 */
    public List<SelectItem> getOnSelectItems() {
        return onSelectItems;
    }

    public void setOnSelectItems(List<SelectItem> list) {
        onSelectItems = list;
    }

    public String toString() {
    	StringBuilder sb=new StringBuilder("DISTINCT");
        if (onSelectItems != null && onSelectItems.size() > 0) {
        	sb.append(" ON ");
            PlainSelect.getStringList(sb,onSelectItems,",",true);
        }
        return sb.toString();
    }
}
