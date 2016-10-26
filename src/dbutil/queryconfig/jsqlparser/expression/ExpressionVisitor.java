package dbutil.queryconfig.jsqlparser.expression;

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
import dbutil.queryconfig.jsqlparser.expression.operators.relational.GreaterThan;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.GreaterThanEquals;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.InExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.IsNullExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.LikeExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.Matches;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.MinorThan;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.MinorThanEquals;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.NotEqualsTo;
import dbutil.queryconfig.jsqlparser.schema.Column;
import dbutil.queryconfig.jsqlparser.statement.select.SubSelect;

public interface ExpressionVisitor {

    public void visit(NullValue nullValue);

    public void visit(Function function);

    public void visit(InverseExpression inverseExpression);

    public void visit(JpqlParameter parameter);
    
    public void visit(JdbcParameter parameter);

    public void visit(DoubleValue doubleValue);

    public void visit(LongValue longValue);

    public void visit(DateValue dateValue);

    public void visit(TimeValue timeValue);

    public void visit(TimestampValue timestampValue);

    public void visit(Parenthesis parenthesis);

    public void visit(StringValue stringValue);

    public void visit(Addition addition);

    public void visit(Division division);

    public void visit(Multiplication multiplication);

    public void visit(Subtraction subtraction);

    public void visit(AndExpression andExpression);

    public void visit(OrExpression orExpression);

    public void visit(Between between);

    public void visit(EqualsTo equalsTo);

    public void visit(GreaterThan greaterThan);

    public void visit(GreaterThanEquals greaterThanEquals);

    public void visit(InExpression inExpression);

    public void visit(IsNullExpression isNullExpression);

    public void visit(LikeExpression likeExpression);

    public void visit(MinorThan minorThan);

    public void visit(MinorThanEquals minorThanEquals);

    public void visit(NotEqualsTo notEqualsTo);

    public void visit(Column tableColumn);

    public void visit(SubSelect subSelect);

    public void visit(CaseExpression caseExpression);

    public void visit(WhenClause whenClause);

    public void visit(ExistsExpression existsExpression);

    public void visit(AllComparisonExpression allComparisonExpression);

    public void visit(AnyComparisonExpression anyComparisonExpression);

    public void visit(Concat concat);

    public void visit(Matches matches);

    public void visit(BitwiseAnd bitwiseAnd);

    public void visit(BitwiseOr bitwiseOr);

    public void visit(BitwiseXor bitwiseXor);
}
