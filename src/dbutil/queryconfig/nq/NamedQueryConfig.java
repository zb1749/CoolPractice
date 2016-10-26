package dbutil.queryconfig.nq;



import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.JpqlParameter;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.*;
import dbutil.queryconfig.jsqlparser.parser.JpqlParser;
import dbutil.queryconfig.jsqlparser.parser.ParseException;
import dbutil.queryconfig.jsqlparser.statement.Statement;
import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;
import dbutil.queryconfig.jsqlparser.statement.select.SelectBody;
import dbutil.queryconfig.jsqlparser.statement.select.Union;
import dbutil.common.Entry;
import dbutil.queryconfig.jsqlparser.SelectToCountWrapper;
import dbutil.queryconfig.jsqlparser.VisitorAdapter;
import dbutil.queryconfig.nq.ParameterProvider.MapProvider;
import dbutil.tools.ArrayUtils;
import dbutil.tools.StringUtils;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.*;



/**
 * Created by Kevin on 2016/10/25.
 */

public class NamedQueryConfig {

    private static final String FRAGMENT_SQL_FORMAT = ":%s<sql>";

    public static final int TYPE_SQL = 0;
    public static final int TYPE_JPQL = 1;

    private String rawsql;

    private String resovledSql;

    private String name;

    /**
     * 设置该命名查询的类型，是SQL，还是JPQL
     */
    private int type;

    private String tag;

    private String remark;

    private int fetchSize;

    private boolean fromDb = false;
    private Map<Object, JpqlParameter> params;
    private Statement statement;
    private dbutil.queryconfig.jsqlparser.statement.select.Select countStatement;

    public String getResovledSql() {
        return resovledSql;
    }

    public void setResovledSql(String resovledSql) {
        this.resovledSql = resovledSql;
    }

    public boolean isFromDb() {
        return fromDb;
    }

    public void setFromDb(boolean fromDb) {
        this.fromDb = fromDb;
    }

    public Statement parseStatement(String sql) throws ParseException {
        JpqlParser parser = new JpqlParser(new StringReader(sql));
        return parser.Statement();
    }

    private void analy(String sql) throws SQLException {
        try {
            Statement st = parseStatement(sql);
            final Map<Object, JpqlParameter> currentParams = new HashMap<Object, JpqlParameter>();
            st.accept(new VisitorAdapter() {//将SQL语句中的schema替换为映射后的schema
                @Override
                public void visit(JpqlParameter param) {
                    currentParams.put(param.getKey(), param);
                }


                @Override
                public void visit(dbutil.queryconfig.jsqlparser.schema.Column c) {
                    String schema = c.getSchema();
                    if (schema == null) {
                        schema = c.getTable().getSchemaName();
                    }


                }
            });
            this.statement = st;
            this.params = currentParams;
        } catch (ParseException e) {
            resetResovledSqlAndStatement(true);

            String message = e.getMessage();
            int n = message.indexOf("Was expecting");
            if (n > -1) {
                message = message.substring(0, n);
            }
            throw new SQLException(StringUtils.concat("Parse error:", sql, "\n", message));
        }
    }

    public NamedQueryConfig(String name, String sql, String type, int fetchSize) {
        this.rawsql = sql;
        this.resovledSql = this.rawsql;
        this.name = name;
        this.fetchSize = fetchSize;
        if ("jpql".equalsIgnoreCase(type)) {
            this.type = TYPE_JPQL;
        } else {
            this.type = TYPE_SQL;
        }
    }

    public synchronized Map<Object, JpqlParameter> getParams() {
        if (params != null) {
            return params;
        }
        if (statement == null) {
            try {
                analy(this.resovledSql);
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return params;
    }

    /**
     * 得到SQL和绑定参数
     */
    public Entry<String, List<Object>> getSqlAndParams(ParameterProvider prov) throws SQLException {
        if (statement == null) {
            analy(this.resovledSql);
        }
        return applyParam(statement, prov);
    }

    /**
     * 得到修改后的count语句和绑定参数 注意只有select语句能修改成count语句
     *
     * @param prov
     * @return
     * @throws SQLException
     */
    public Entry<String, List<Object>> getCountSqlAndParams(ParameterProvider prov) throws SQLException {
        if (statement == null) {
            analy(this.resovledSql);
        }
        if (countStatement == null) {
            if (statement instanceof dbutil.queryconfig.jsqlparser.statement.select.Select) {
                SelectBody oldBody = ((dbutil.queryconfig.jsqlparser.statement.select.Select) statement).getSelectBody();
                SelectToCountWrapper body = null;
                if (oldBody instanceof PlainSelect) {
                    body = new SelectToCountWrapper((PlainSelect) oldBody);
                } else if (oldBody instanceof Union) {
                    body = new SelectToCountWrapper((Union) oldBody);
                }
                if (body == null) {
                    throw new SQLException("Can not generate count SQL statement for " + statement.getClass().getName());
                }
                countStatement = new dbutil.queryconfig.jsqlparser.statement.select.Select();
                countStatement.setSelectBody(body);
            }
        }
        return applyParam(countStatement, prov);
    }

    private final static class ParamApplier extends VisitorAdapter {
        private ParameterProvider prov;
        private List<Object> params;

        public ParamApplier(ParameterProvider prov, List<Object> params) {
            this.prov = prov;
            this.params = params;
        }

        // 计算绑定变量
        @Override
        public void visit(JpqlParameter param) {
            Object value = null;
            boolean contains;
            param.setResolved(0);
            if (param.isIndexParam()) {
                value = prov.getIndexedParam(param.getIndex());
                contains = prov.containsParam(param.getIndex());
            } else {
                value = prov.getNamedParam(param.getName());
                contains = prov.containsParam(param.getName());
            }

            if (value != null) {
                if (value.getClass().isArray()) {
                    int size = ArrayUtils.length(value);
                    param.setResolved(size);
                    if (value.getClass().getComponentType().isPrimitive()) {
                        value = ArrayUtils.toObject(value);
                    }
                    for (Object v : (Object[]) value) {
                        params.add(v);
                    }
                } else if (value instanceof Collection) {
                    int size = ((Collection<?>) value).size();
                    param.setResolved(size);
                    for (Object v : (Collection<?>) value) {
                        params.add(v);
                    }
                } else {
                    params.add(value);
                }
            } else {
                if (contains) {
                    params.add(value);
                }
            }
        }

        @Override
        public void visit(NotEqualsTo notEqualsTo) {
            super.visit(notEqualsTo);
            notEqualsTo.setEmpty(false);
            if (notEqualsTo.getRightExpression() instanceof JpqlParameter) {
                if (!prov.containsParam(((JpqlParameter) notEqualsTo.getRightExpression()).getKey())) {
                    notEqualsTo.setEmpty(true);
                }
            }
        }

        @Override
        public void visit(InExpression inExpression) {
            super.visit(inExpression);
            inExpression.setEmpty(false);
            if (inExpression.getItemsList() instanceof ExpressionList) {
                ExpressionList list0 = (ExpressionList) inExpression.getItemsList();
                List<Expression> list = list0.getExpressions();
                if (list.size() == 1 && (list.get(0) instanceof JpqlParameter)) {
                    JpqlParameter p = (JpqlParameter) list.get(0);
                    if (!prov.containsParam(p.getKey())) {
                        inExpression.setEmpty(true);
                    }
                }
            }
        }

        @Override
        public void visit(EqualsTo equalsTo) {
            super.visit(equalsTo);
            equalsTo.setEmpty(false);
            if (equalsTo.getRightExpression() instanceof JpqlParameter) {
                if (!prov.containsParam(((JpqlParameter) equalsTo.getRightExpression()).getKey())) {
                    equalsTo.setEmpty(true);
                }
            }
        }

        @Override
        public void visit(MinorThan minorThan) {
            super.visit(minorThan);
            minorThan.setEmpty(false);
            if (minorThan.getRightExpression() instanceof JpqlParameter) {
                if (!prov.containsParam(((JpqlParameter) minorThan.getRightExpression()).getKey())) {
                    minorThan.setEmpty(true);
                }
            }
        }

        @Override
        public void visit(MinorThanEquals minorThanEquals) {
            super.visit(minorThanEquals);
            minorThanEquals.setEmpty(false);
            if (minorThanEquals.getRightExpression() instanceof JpqlParameter) {
                if (!prov.containsParam(((JpqlParameter) minorThanEquals.getRightExpression()).getKey())) {
                    minorThanEquals.setEmpty(true);
                }
            }
        }

        @Override
        public void visit(GreaterThan greaterThan) {
            super.visit(greaterThan);
            greaterThan.setEmpty(false);
            if (greaterThan.getRightExpression() instanceof JpqlParameter) {
                if (!prov.containsParam(((JpqlParameter) greaterThan.getRightExpression()).getKey())) {
                    greaterThan.setEmpty(true);
                }
            }
        }

        @Override
        public void visit(GreaterThanEquals greaterThanEquals) {
            super.visit(greaterThanEquals);
            greaterThanEquals.setEmpty(false);
            if (greaterThanEquals.getRightExpression() instanceof JpqlParameter) {
                if (!prov.containsParam(((JpqlParameter) greaterThanEquals.getRightExpression()).getKey())) {
                    greaterThanEquals.setEmpty(true);
                }
            }
        }

        @Override
        public void visit(LikeExpression likeExpression) {
            super.visit(likeExpression);
            likeExpression.setEmpty(false);
            if (likeExpression.getRightExpression() instanceof JpqlParameter) {
                if (!prov.containsParam(((JpqlParameter) likeExpression.getRightExpression()).getKey())) {
                    likeExpression.setEmpty(true);
                }
            }
        }
    }

    /**
     * 在指定的SQL表达式中应用参数
     *
     * @param ex
     * @param prov
     * @return
     */
    public static Entry<String, List<Object>> applyParam(Expression ex, MapProvider prov) {
        final List<Object> params = new ArrayList<Object>();
        ex.accept(new ParamApplier(prov, params));
        return new Entry<String, List<Object>>(ex.toString(), params);
    }

    /*
     * 返回应用参数后的查询
     */
    public static Entry<String, List<Object>> applyParam(Statement st, final ParameterProvider prov) {
        final List<Object> params = new ArrayList<Object>();
        st.accept(new ParamApplier(prov, params));
        return new Entry<String, List<Object>>(st.toString(), params);
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String toString() {
        return rawsql;
    }

    public static NamedQueryConfig getBySys(String name) {
        NamedQueryConfig nc = NamedQueryHolder.getInstance().get(name);
        if (nc == null) {
            throw new IllegalArgumentException("The query which named " + name + " not found.");
        }
        return nc;
    }

    public <T> NativeQuery<T> createNativeQuery(Class<T> reClass) {
        return new NativeQuery<T>(this, reClass);
    }

    /**
     * 根据配置创建NativeQuery
     *
     * @param reClass
     * @return
     */
    public static <T> NativeQuery<T> createNativeQuery(String name, Class<T> reClass) {

        NamedQueryConfig nc = NamedQueryHolder.getInstance().get(name);
        if (nc == null) {
            throw new IllegalArgumentException("The query which named " + name + " not found.");
        }
        return new NativeQuery<T>(nc, reClass);
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRawsql() {
        return rawsql;
    }

    public void setRawsql(String rawsql) {
        this.rawsql = rawsql;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public void applyFragment(String name, String value) {
        resetResovledSqlAndStatement(false);
        String fragment = String.format(FRAGMENT_SQL_FORMAT, name);
        resovledSql = resovledSql.replaceAll(fragment, value);
    }

    private void resetResovledSqlAndStatement(boolean forceReset) {
        if (forceReset || statement != null) { // statement != null 说明已经分析过SQL了，需要重置
            statement = null;
            countStatement = null;
            resovledSql = rawsql;
        }
    }

    public enum Field {
        rawsql, name, type, tag, remark, fetchSize
    }

}
