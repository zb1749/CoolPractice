package dbutil.queryconfig.jsqlparser;

import java.util.Iterator;

import dbutil.queryconfig.jsqlparser.expression.AllComparisonExpression;
import dbutil.queryconfig.jsqlparser.expression.AnyComparisonExpression;
import dbutil.queryconfig.jsqlparser.expression.BinaryExpression;
import dbutil.queryconfig.jsqlparser.expression.CaseExpression;
import dbutil.queryconfig.jsqlparser.expression.DateValue;
import dbutil.queryconfig.jsqlparser.expression.DoubleValue;
import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.ExpressionVisitor;
import dbutil.queryconfig.jsqlparser.expression.Function;
import dbutil.queryconfig.jsqlparser.expression.InverseExpression;
import dbutil.queryconfig.jsqlparser.expression.JdbcParameter;
import dbutil.queryconfig.jsqlparser.expression.JpqlParameter;
import dbutil.queryconfig.jsqlparser.expression.LongValue;
import dbutil.queryconfig.jsqlparser.expression.NullValue;
import dbutil.queryconfig.jsqlparser.expression.Parenthesis;
import dbutil.queryconfig.jsqlparser.expression.StringValue;
import dbutil.queryconfig.jsqlparser.expression.TimeValue;
import dbutil.queryconfig.jsqlparser.expression.TimestampValue;
import dbutil.queryconfig.jsqlparser.expression.WhenClause;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.Addition;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.Concat;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.Division;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.Multiplication;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.Subtraction;
import dbutil.queryconfig.jsqlparser.expression.operators.conditional.AndExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.conditional.OrExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.Between;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.EqualsTo;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ExistsExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ExpressionList;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.GreaterThan;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.GreaterThanEquals;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.InExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.IsNullExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ItemsListVisitor;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.LikeExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.Matches;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.MinorThan;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.MinorThanEquals;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.NotEqualsTo;
import dbutil.queryconfig.jsqlparser.schema.Column;
import dbutil.queryconfig.jsqlparser.schema.Table;
import dbutil.queryconfig.jsqlparser.statement.StatementVisitor;
import dbutil.queryconfig.jsqlparser.statement.create.table.CreateTable;
import dbutil.queryconfig.jsqlparser.statement.delete.Delete;
import dbutil.queryconfig.jsqlparser.statement.drop.Drop;
import dbutil.queryconfig.jsqlparser.statement.insert.Insert;
import dbutil.queryconfig.jsqlparser.statement.replace.Replace;
import dbutil.queryconfig.jsqlparser.statement.select.AllColumns;
import dbutil.queryconfig.jsqlparser.statement.select.AllTableColumns;
import dbutil.queryconfig.jsqlparser.statement.select.FromItemVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.Join;
import dbutil.queryconfig.jsqlparser.statement.select.OrderByElement;
import dbutil.queryconfig.jsqlparser.statement.select.OrderByVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;
import dbutil.queryconfig.jsqlparser.statement.select.Select;
import dbutil.queryconfig.jsqlparser.statement.select.SelectExpressionItem;
import dbutil.queryconfig.jsqlparser.statement.select.SelectItem;
import dbutil.queryconfig.jsqlparser.statement.select.SelectItemVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.SelectVisitor;
import dbutil.queryconfig.jsqlparser.statement.select.SubJoin;
import dbutil.queryconfig.jsqlparser.statement.select.SubSelect;
import dbutil.queryconfig.jsqlparser.statement.select.Union;
import dbutil.queryconfig.jsqlparser.statement.truncate.Truncate;
import dbutil.queryconfig.jsqlparser.statement.update.Update;


public class VisitorAdapter implements SelectVisitor, FromItemVisitor, ExpressionVisitor, ItemsListVisitor, StatementVisitor, SelectItemVisitor,OrderByVisitor {

    public void visit(PlainSelect plainSelect) {
        plainSelect.getFromItem().accept(this);
        for (SelectItem s : plainSelect.getSelectItems()) {
            s.accept(this);
        }
        if (plainSelect.getJoins() != null) {
            for (Iterator<Join> joinsIt = plainSelect.getJoins().iterator(); joinsIt.hasNext(); ) {
                Join join = (Join) joinsIt.next();
                join.getRightItem().accept(this);
                if(join.getOnExpression()!=null){
                	join.getOnExpression().accept(this);	
                }
                if(join.getUsingColumns()!=null){
                	for(Column c:join.getUsingColumns()){
                    	c.accept(this);
                    }	
                }
            }
        }
        if (plainSelect.getWhere() != null) plainSelect.getWhere().accept(this);
        
        if (plainSelect.getStartWith()!= null) plainSelect.getStartWith().accept(this);
        
        if(plainSelect.getHaving()!=null) plainSelect.getHaving().accept(this);
        
        if (plainSelect.getOrderByElements() != null) {
        	for(OrderByElement e:plainSelect.getOrderByElements()){
        		e.accept(this);
        	}
        }
    }

    public void visit(Union union) {
        for (Iterator<PlainSelect> iter = union.getPlainSelects().iterator(); iter.hasNext(); ) {
            PlainSelect plainSelect = iter.next();
            visit(plainSelect);
        }
    }

    public void visit(SubSelect subSelect) {
        subSelect.getSelectBody().accept(this);
    }

    public void visit(Addition addition) {
        visitBinaryExpression(addition);
    }

    public void visit(AndExpression andExpression) {
        visitBinaryExpression(andExpression);
    }

    public void visit(Between between) {
        between.getLeftExpression().accept(this);
        between.getBetweenExpressionStart().accept(this);
        between.getBetweenExpressionEnd().accept(this);
    }

    public void visit(Division division) {
        visitBinaryExpression(division);
    }

    public void visit(DoubleValue doubleValue) {
    }

    public void visit(EqualsTo equalsTo) {
        visitBinaryExpression(equalsTo);
    }

    public void visit(Function function) {
    	if(function.getParameters()!=null)
    		function.getParameters().accept(this);
    }

    public void visit(GreaterThan greaterThan) {
        visitBinaryExpression(greaterThan);
    }

    public void visit(GreaterThanEquals greaterThanEquals) {
        visitBinaryExpression(greaterThanEquals);
    }

    public void visit(InExpression inExpression) {
        inExpression.getLeftExpression().accept(this);
        inExpression.getItemsList().accept(this);
    }

    public void visit(InverseExpression inverseExpression) {
        inverseExpression.getExpression().accept(this);
    }

    public void visit(IsNullExpression isNullExpression) {
    }

    public void visit(LikeExpression likeExpression) {
        visitBinaryExpression(likeExpression);
    }

    public void visit(ExistsExpression existsExpression) {
        existsExpression.getRightExpression().accept(this);
    }

    public void visit(LongValue longValue) {
    }

    public void visit(MinorThan minorThan) {
        visitBinaryExpression(minorThan);
    }

    public void visit(MinorThanEquals minorThanEquals) {
        visitBinaryExpression(minorThanEquals);
    }

    public void visit(Multiplication multiplication) {
        visitBinaryExpression(multiplication);
    }

    public void visit(NotEqualsTo notEqualsTo) {
        visitBinaryExpression(notEqualsTo);
    }

    public void visit(NullValue nullValue) {
    }

    public void visit(OrExpression orExpression) {
        visitBinaryExpression(orExpression);
    }

    public void visit(Parenthesis parenthesis) {
        parenthesis.getExpression().accept(this);
    }

    public void visit(StringValue stringValue) {
    }

    public void visit(Subtraction subtraction) {
        visitBinaryExpression(subtraction);
    }

    public void visitBinaryExpression(BinaryExpression binaryExpression) {
        binaryExpression.getLeftExpression().accept(this);
        binaryExpression.getRightExpression().accept(this);
    }

    public void visit(ExpressionList expressionList) {
        for (Iterator<Expression> iter = expressionList.getExpressions().iterator(); iter.hasNext(); ) {
            Expression expression = (Expression) iter.next();
            expression.accept(this);
        }
    }

    public void visit(JdbcParameter jdbcParameter) {
    }
    
    public void visit(DateValue dateValue) {
    }

    public void visit(TimestampValue timestampValue) {
    }

    public void visit(TimeValue timeValue) {
    }

    public void visit(CaseExpression caseExpression) {
    	if(caseExpression.getElseExpression()!=null){
    		caseExpression.getElseExpression().accept(this);
    	}
        if(caseExpression.getSwitchExpression()!=null){
        	caseExpression.getSwitchExpression().accept(this);	
        }
    }

    public void visit(WhenClause whenClause) {
    	whenClause.getWhenExpression().accept(this);
        whenClause.getThenExpression().accept(this);
    }

    public void visit(AllComparisonExpression allComparisonExpression) {
        allComparisonExpression.GetSubSelect().getSelectBody().accept(this);
    }

    public void visit(AnyComparisonExpression anyComparisonExpression) {
        anyComparisonExpression.GetSubSelect().getSelectBody().accept(this);
    }

    public void visit(SubJoin subjoin) {
        subjoin.getLeft().accept(this);
        subjoin.getJoin().getRightItem().accept(this);
    }

    public void visit(Concat concat) {
        visitBinaryExpression(concat);
    }

    public void visit(Matches matches) {
        visitBinaryExpression(matches);
    }

    public void visit(BitwiseAnd bitwiseAnd) {
        visitBinaryExpression(bitwiseAnd);
    }

    public void visit(BitwiseOr bitwiseOr) {
        visitBinaryExpression(bitwiseOr);
    }

    public void visit(BitwiseXor bitwiseXor) {
        visitBinaryExpression(bitwiseXor);
    }

    public void visit(Column tableColumn) {
    }

    public void visit(Table tableName) {
    }

    public void visit(Select select) {
        if (select.getSelectBody() != null) select.getSelectBody().accept(this);
    }

    public void visit(Delete delete) {
        delete.getTable().accept(this);
        if(delete.getWhere()!=null){
        	delete.getWhere().accept(this);	
        }
    }

    public void visit(Update update) {
        update.getTable().accept(this);
        for (Column c : update.getColumns()) {
            visit(c);
        }
        for (Expression ex : update.getExpressions()) {
            ex.accept(this);
        }
        if(update.getWhere()!=null){
        	update.getWhere().accept(this);	
        }
    }

    public void visit(Insert insert) {
    	if(insert.getColumns()!=null){
    		for (Column c : insert.getColumns()) {
                visit(c);
            }	
    	}
        insert.getTable().accept(this);
        insert.getItemsList().accept(this);
    }

    public void visit(Replace replace) {
        for (Column c : replace.getColumns()) {
            visit(c);
        }
        for (Expression ex : replace.getExpressions()) {
            ex.accept(this);
        }
        replace.getTable().accept(this);
        replace.getItemsList().accept(this);
    }

    public void visit(Drop drop) {
    }

    public void visit(Truncate truncate) {
        truncate.getTable().accept(this);
    }

    public void visit(CreateTable createTable) {
        createTable.getTable().accept(this);
    }

    public void visit(AllColumns allColumns) {
    }

    public void visit(AllTableColumns allTableColumns) {
    }

    public void visit(SelectExpressionItem selectExpressionItem) {
        selectExpressionItem.getExpression().accept(this);
    }

	public void visit(JpqlParameter parameter) {
	}
	
	public void visit(OrderByElement orderBy) {
		orderBy.getExpression().accept(this);
	}
}
