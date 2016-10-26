package dbutil.queryconfig.jsqlparser.expression.operators.relational;

/**
 * Values of an "INSERT" statement (for example a SELECT or a list of expressions)
 */
public interface ItemsList {

    public void accept(ItemsListVisitor itemsListVisitor);
}
