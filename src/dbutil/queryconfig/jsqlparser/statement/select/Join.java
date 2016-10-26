package dbutil.queryconfig.jsqlparser.statement.select;

import java.util.List;

import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.schema.Column;

/**
 * A join clause
 */
public class Join {

	private boolean outer = false;

	private boolean right = false;

	private boolean left = false;

	private boolean natural = false;

	private boolean full = false;

	private boolean inner = false;

	private boolean simple = false;

	private FromItem rightItem;

	private Expression onExpression;

	private List<Column> usingColumns;

	/**
	 * Whether is a tab1,tab2 join
	 * 
	 * @return true if is a "tab1,tab2" join
	 */
	public boolean isSimple() {
		return simple;
	}

	public void setSimple(boolean b) {
		simple = b;
	}

	/**
	 * Whether is a "INNER" join
	 * 
	 * @return true if is a "INNER" join
	 */
	public boolean isInner() {
		return inner;
	}

	public void setInner(boolean b) {
		inner = b;
	}

	/**
	 * Whether is a "OUTER" join
	 * 
	 * @return true if is a "OUTER" join
	 */
	public boolean isOuter() {
		return outer;
	}

	public void setOuter(boolean b) {
		outer = b;
	}

	/**
	 * Whether is a "LEFT" join
	 * 
	 * @return true if is a "LEFT" join
	 */
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean b) {
		left = b;
	}

	/**
	 * Whether is a "RIGHT" join
	 * 
	 * @return true if is a "RIGHT" join
	 */
	public boolean isRight() {
		return right;
	}

	public void setRight(boolean b) {
		right = b;
	}

	/**
	 * Whether is a "NATURAL" join
	 * 
	 * @return true if is a "NATURAL" join
	 */
	public boolean isNatural() {
		return natural;
	}

	public void setNatural(boolean b) {
		natural = b;
	}

	/**
	 * Whether is a "FULL" join
	 * 
	 * @return true if is a "FULL" join
	 */
	public boolean isFull() {
		return full;
	}

	public void setFull(boolean b) {
		full = b;
	}

	/**
	 * Returns the right item of the join
	 */
	public FromItem getRightItem() {
		return rightItem;
	}

	public void setRightItem(FromItem item) {
		rightItem = item;
	}

	/**
	 * Returns the "ON" expression (if any)
	 */
	public Expression getOnExpression() {
		return onExpression;
	}

	public void setOnExpression(Expression expression) {
		onExpression = expression;
	}

	/**
	 * Returns the "USING" list of {@link Column}
	 * s (if any)
	 */
	public List<Column> getUsingColumns() {
		return usingColumns;
	}

	public void setUsingColumns(List<Column> list) {
		usingColumns = list;
	}

	public String toString() {
		if (isSimple())
			return "" + rightItem;

		StringBuilder sb=new StringBuilder();
		if (isRight())
			sb.append("RIGHT ");
		else if (isNatural())
			sb.append("NATURAL ");
		else if (isFull())
			sb.append("FULL ");
		else if (isLeft())
			sb.append("LEFT ");
		if (isOuter())
			sb.append("OUTER ");
		else if (isInner())
			sb.append("INNER ");
		sb.append("JOIN ").append(rightItem);
		if(onExpression != null){
			sb.append(" ON ");
			sb.append(onExpression);
		}
		PlainSelect.getFormatedList(sb,usingColumns, "USING", true);
		return sb.toString();
	}
}
