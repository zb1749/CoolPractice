package dbutil;

import dbutil.portal.*;
import dbutil.common.Entry;
import dbutil.common.ExecuteSqlObjCache;

import dbutil.queryconfig.nq.NamedQueryConfig;
import dbutil.queryconfig.nq.NativeQuery;
import dbutil.tools.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;
import java.util.Date;


/**
 * SQL执行类，用于查询数据库
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class QueryUtil extends HibernateDaoSupport implements BaseUtil, ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = Logger.getLogger(QueryUtil.class);

    private static ApplicationContext applicationContext = null;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (this.applicationContext == null) {
            this.applicationContext = event.getApplicationContext();
        }
    }

    @Resource(name = "sessionFactory")
    public void getHibernateSession(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }


    /**
     * 由于当前方法返回的DataSource获取的连接不在事务中管理，建议不使用，用getConnection()直接获取一个事务型的连接
     *
     * @return
     */
    @Deprecated
    public DataSource getDataSource() {
        return SessionFactoryUtils.getDataSource(getSessionFactory());
    }

    public QueryUtil() {

    }

    public static QueryUtil getInstance() {
        //修改成applicationContext读取bean方式，用于junit测试
        //WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        if (ExecuteSqlObjCache.queryCache.size() == 0) {
            ExecuteSqlObjCache esc = (ExecuteSqlObjCache) applicationContext.getBean("executeSqlObjCache");
            esc.init();
        }
        return ExecuteSqlObjCache.queryCache.get(SQLPretreatment.defaultDataSourceName);
    }

    public static QueryUtil getInstance(String sessionFactoryName) {
        //修改成applicationContext读取bean方式，用于junit测试
        //WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

        if (ExecuteSqlObjCache.queryCache.size() == 0) {
            ExecuteSqlObjCache esc = (ExecuteSqlObjCache) applicationContext.getBean("executeSqlObjCache");
            esc.init();
        }
        return ExecuteSqlObjCache.queryCache.get(sessionFactoryName);
    }

    public QueryUtil getSqlExeInstance(String nqName) {
        //从系统中取出SQL模板
        NamedQueryConfig nqcTemplate = NamedQueryConfig.getBySys(nqName);
        String sfName = nqcTemplate.getTag();
        if (sfName == null || "".equals(sfName))
            sfName = SQLPretreatment.defaultDataSourceName;
        else
            logger.info("用户自定义数据源:" + sfName);
        return ExecuteSqlObjCache.queryCache.get(sfName);
    }

    /**
     * 装载参数
     *
     * @param ps
     * @param params
     * @throws SQLException
     */
    private void putParamToSql(PreparedStatement ps, List<Object> params) throws SQLException {
        for (int i = 0; params != null && i < params.size(); i++) {
            Object obj = params.get(i);
            if (obj == null) {
                ps.setNull(i + 1, Types.NULL);
            } else {
                if (obj instanceof java.sql.Date)
                    ps.setDate(i + 1, (java.sql.Date) obj);
                else if (obj instanceof Timestamp)
                    ps.setTimestamp(i + 1, (Timestamp) obj);
                else if (obj instanceof String)
                    ps.setString(i + 1, (String) params.get(i));
                else if (obj instanceof Long)
                    ps.setLong(i + 1, (Long) params.get(i));
                else if (obj instanceof Integer)
                    ps.setInt(i + 1, (Integer) params.get(i));
                else if (obj instanceof String)
                    ps.setObject(i + 1, params.get(i));
            }
        }
    }

    /**
     * 自定义sql,返回Object类型的结果列
     *
     * @param sql    自定义SQL
     * @param params sql中的参数
     * @return
     */
    public List<Object[]> findObjBySql(String sql, Map<String, Object> params) throws RuntimeException {
        return findObjBySql(sql, params, null);
    }

    /**
     * 自定义sql,返回Object类型的结果列
     *
     * @param sql    自定义SQL
     * @param params sql中的参数
     * @param obe    排序
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List<Object[]> findObjBySql(String sql, Map<String, Object> params, OrderByElement obe) throws RuntimeException {
        try {
            sql = appendOrder(sql, obe);
            if (params == null)
                return executeSql(sql, null);
            NamedQueryConfig nqc = new NamedQueryConfig("", sql, "", 0);
            NativeQuery<Entry> nQuery = nqc.createNativeQuery(Entry.class);
            nQuery.setParameterMap(params);
            Entry<String, List<Object>> parse = nqc.getSqlAndParams(nQuery);
            return executeSql(parse.getKey(), parse.getValue());
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 自定义SQL分页查询，返回Object类型的列表结果
     *
     * @param sql          自定义SQL
     * @param params       sql中的参数
     * @param currentPage
     * @param countPerPage
     * @return
     */
    public List<Object[]> findObjBySql(String sql, Map<String, Object> params, int currentPage, int countPerPage) throws RuntimeException {
        return findObjBySql(sql, params, currentPage, countPerPage, null);
    }

    /**
     * 自定义SQL分页查询，返回Object类型的列表结果
     *
     * @param sql          自定义SQL
     * @param params       sql中的参数
     * @param currentPage
     * @param countPerPage
     * @param obe          排序
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List<Object[]> findObjBySql(String sql, Map<String, Object> params, int currentPage, int countPerPage, OrderByElement obe) throws RuntimeException {
        try {
            sql = appendOrder(sql, obe);
            if (params == null)
                return executeSql(createPageSql(sql, currentPage, countPerPage), null);
            NamedQueryConfig nqc = new NamedQueryConfig("", sql, "", 0);
            NativeQuery<Entry> nQuery = nqc.createNativeQuery(Entry.class);
            nQuery.setParameterMap(params);
            Entry<String, List<Object>> parse = nqc.getSqlAndParams(nQuery);
            return executeSql(createPageSql(parse.getKey(), currentPage, countPerPage), parse.getValue());
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 自定义SQL查询，返回指定类型的列表结果
     *
     * @param sql    自定义SQL
     * @param params sql中的参数
     * @param clazz  返回类型
     * @return
     */
    public <T> List<T> findBySql(String sql, Map<String, Object> params, Class<T> clazz) throws RuntimeException {
        return findBySql(sql, params, null, clazz);
    }

    /**
     * 自定义SQL查询，返回指定类型的列表结果
     *
     * @param sql    自定义SQL
     * @param params sql中的参数
     * @param obe    排序
     * @param clazz  返回类型
     * @return
     */
    @SuppressWarnings("rawtypes")
    public <T> List<T> findBySql(String sql, Map<String, Object> params, OrderByElement obe, Class<T> clazz) throws RuntimeException {
        try {
            sql = appendOrder(sql, obe);
            if (params == null)
                return executeSql(sql, null, clazz);
            NamedQueryConfig nqc = new NamedQueryConfig("", sql, "", 0);
            NativeQuery<Entry> nQuery = nqc.createNativeQuery(Entry.class);
            nQuery.setParameterMap(params);
            Entry<String, List<Object>> parse = nqc.getSqlAndParams(nQuery);
            return executeSql(parse.getKey(), parse.getValue(), clazz);
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 自定义SQL分页查询，返回指定类型的列表结果
     *
     * @param sql          自定义SQL
     * @param params       sql中的参数
     * @param currentPage
     * @param countPerPage
     * @param clazz        返回类型
     * @return
     */
    public <T> List<T> findBySql(String sql, Map<String, Object> params, int currentPage, int countPerPage, Class<T> clazz) throws RuntimeException {
        return findBySql(sql, params, currentPage, countPerPage, null, clazz);
    }

    /**
     * 自定义SQL分页查询，返回指定类型的列表结果
     *
     * @param sql          自定义SQL
     * @param params       sql中的参数
     * @param currentPage
     * @param countPerPage
     * @param obe          排序
     * @param clazz        返回类型
     * @return
     */
    @SuppressWarnings("rawtypes")
    public <T> List<T> findBySql(String sql, Map<String, Object> params, int currentPage, int countPerPage, OrderByElement obe, Class<T> clazz) throws RuntimeException {
        try {
            sql = appendOrder(sql, obe);
            if (params == null)
                return executeSql(createPageSql(sql, currentPage, countPerPage), null, clazz);
            NamedQueryConfig nqc = new NamedQueryConfig("", sql, "", 0);
            NativeQuery<Entry> nQuery = nqc.createNativeQuery(Entry.class);
            nQuery.setParameterMap(params);
            Entry<String, List<Object>> parse = nqc.getSqlAndParams(nQuery);
            return executeSql(createPageSql(parse.getKey(), currentPage, countPerPage), parse.getValue(), clazz);
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 分页查询
     *
     * @param sql
     * @param params
     * @param currentPage
     * @param countPerPage
     * @param obe
     * @param clazz
     * @return
     */
    public <T> Page<T> findPageBySql(String sql, Map<String, Object> params, int currentPage, int countPerPage, OrderByElement obe, Class<T> clazz) throws SQLException {
        Page<T> page = new Page<T>();
        int count = getCountBySql(sql, params);
        if (count > 0) {
            page.setTotalCount(count);
            page.setList(findBySql(sql, params, currentPage, countPerPage, obe, clazz));
        } else {
            page.setTotalCount(count);
            page.setList(new ArrayList<T>());
        }
        return page;
    }

    /**
     * 分页查询
     *
     * @param nqName
     * @param params
     * @param currentPage
     * @param countPerPage
     * @param obe
     * @param clazz
     * @return
     */
    public <T> Page<T> findPageByNq(String nqName, Map<String, Object> params, int currentPage, int countPerPage, OrderByElement obe, Class<T> clazz) throws RuntimeException {
        QueryUtil qu = getSqlExeInstance(nqName);
        Page<T> page = new Page<T>();
        int count = qu.getCount(nqName, params);
        if (count > 0) {
            page.setTotalCount(count);
            page.setList(qu.findByNq(nqName, params, currentPage, countPerPage, obe, clazz));
        } else {
            page.setTotalCount(count);
            page.setList(new ArrayList<T>());
        }
        return page;
    }

    /**
     * 通过namequery查询，返回数据集，无指定类型
     *
     * @param nqName
     * @param params sql中的参数
     * @return
     */
    public List<Object[]> findObjByNq(String nqName, Map<String, Object> params) throws RuntimeException {
        QueryUtil qu = getSqlExeInstance(nqName);
        return qu.findObjByNq(nqName, params, null);
    }

    /**
     * 通过namequery查询，返回数据集，无指定类型
     *
     * @param nqName
     * @param params sql中的参数
     * @param obe    排序
     * @return
     */
    public List<Object[]> findObjByNq(String nqName, Map<String, Object> params, OrderByElement obe) throws RuntimeException {
        try {
            QueryUtil qu = getSqlExeInstance(nqName);
            Entry<String, List<Object>> parse = SQLPretreatment.createEntry(nqName, params);
            String sql = parse.getKey();
            sql = appendOrder(sql, obe);
            List<Object> paramObj = parse.getValue();
            return qu.executeSql(nqName, sql, paramObj);
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过namequery查询，返回数据集，无指定类型
     *
     * @param nqName
     * @param params       sql中的参数
     * @param currentPage
     * @param countPerPage
     * @return
     */
    public List<Object[]> findObjByNq(String nqName, Map<String, Object> params, int currentPage, int countPerPage) throws RuntimeException {
        QueryUtil qu = getSqlExeInstance(nqName);
        return qu.findObjByNq(nqName, params, currentPage, countPerPage, null);
    }


    /**
     * 通过namequery查询，返回数据集，无指定类型
     *
     * @param nqName
     * @param params       sql中的参数
     * @param currentPage
     * @param countPerPage
     * @param obe
     * @return
     */
    public List<Object[]> findObjByNq(String nqName, Map<String, Object> params, int currentPage, int countPerPage, OrderByElement obe) throws RuntimeException {
        try {
            QueryUtil qu = getSqlExeInstance(nqName);
            Entry<String, List<Object>> parse = SQLPretreatment.createEntry(nqName, params);
            String sql = parse.getKey();
            sql = appendOrder(sql, obe);
            return qu.executeSql(nqName, createPageSql(sql, currentPage, countPerPage), parse.getValue());
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过namequery分页查询，并根据指定类型返回
     *
     * @param nqName
     * @param params       sql中的参数
     * @param currentPage
     * @param countPerPage
     * @param clazz
     * @return
     */
    public <T> List<T> findByNq(String nqName, Map<String, Object> params, int currentPage, int countPerPage, Class<T> clazz) throws RuntimeException {
        QueryUtil qu = getSqlExeInstance(nqName);
        return qu.findByNq(nqName, params, currentPage, countPerPage, null, clazz);
    }

    /**
     * 通过namequery分页查询，并根据指定类型返回
     *
     * @param nqName
     * @param params       sql中的参数
     * @param currentPage
     * @param countPerPage
     * @param obe          排序
     * @param clazz
     * @return
     */
    public <T> List<T> findByNq(String nqName, Map<String, Object> params, int currentPage, int countPerPage, OrderByElement obe, Class<T> clazz) throws RuntimeException {
        try {
            QueryUtil qu = getSqlExeInstance(nqName);
            Entry<String, List<Object>> parse = SQLPretreatment.createEntry(nqName, params);
            String sql = parse.getKey();
            sql = appendOrder(sql, obe);
            List<Object> paramObj = parse.getValue();
            return qu.executeSql(nqName, createPageSql(sql, currentPage, countPerPage), paramObj, clazz);
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过namequery查询，并根据指定类型返回
     *
     * @param nqName
     * @param params sql中的参数
     * @param clazz
     * @return
     */
    public <T> List<T> findByNq(String nqName, Map<String, Object> params, Class<T> clazz) throws RuntimeException {
        QueryUtil qu = getSqlExeInstance(nqName);
        return qu.findByNq(nqName, params, null, clazz);
    }

    /**
     * 通过namequery查询，并根据指定类型返回
     *
     * @param nqName
     * @param params sql中的参数
     * @param obe    排序
     * @param clazz
     * @return
     */
    public <T> List<T> findByNq(String nqName, Map<String, Object> params, OrderByElement obe, Class<T> clazz) throws RuntimeException {
        try {
            QueryUtil qu = getSqlExeInstance(nqName);
            Entry<String, List<Object>> parse = SQLPretreatment.createEntry(nqName, params);
            String sql = parse.getKey();
            sql = appendOrder(sql, obe);
            List<Object> paramObj = parse.getValue();
            return qu.executeSql(nqName, sql, paramObj, clazz);
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 命名查询单个数据
     *
     * @param nqName
     * @param params
     * @param clazz
     * @return
     */
    public <T> T findSingleByNq(String nqName, Map<String, Object> params, Class<T> clazz) throws RuntimeException {
        try {
            QueryUtil qu = getSqlExeInstance(nqName);
            Entry<String, List<Object>> parse = SQLPretreatment.createEntry(nqName, params);
            String sql = parse.getKey();
            List<Object> paramObj = parse.getValue();
            List<T> result = qu.executeSql(nqName, sql, paramObj, clazz);
            if (result != null && result.size() > 0)
                return result.get(0);
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 自定义SQL查询单个数据
     *
     * @param sql
     * @param params
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
    public <T> T findSingleBySql(String sql, Map<String, Object> params, Class<T> clazz) throws RuntimeException {
        try {
            NamedQueryConfig nqc = new NamedQueryConfig("", sql, "", 0);
            NativeQuery<Entry> nQuery = nqc.createNativeQuery(Entry.class);
            nQuery.setParameterMap(params);
            Entry<String, List<Object>> parse = nqc.getSqlAndParams(nQuery);
            sql = parse.getKey();
            List<Object> paramObj = parse.getValue();
            List<T> result = executeSql(sql, paramObj, clazz);
            if (result != null && result.size() > 0)
                return result.get(0);
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 指定NameQuery的名称，获取对应的SQL，不解析该SQL，直接给SQL设置参数并执行，<br/>
     * 该方法需要注意: SQL中的参数必须和params的参数顺序，个数保持一致，并且使用占位符 ? 作为参数
     * <br/><br/>
     * 建议在SQL解析器无法解析SQL语句时，才用该方法
     *
     * @param nqName
     * @param params 参数列表
     */
    public <T> List<T> findFinalByNq(String nqName, List<Object> params, OrderByElement obe, Class<T> clazz) throws RuntimeException {
        try {
            QueryUtil qu = getSqlExeInstance(nqName);
            NamedQueryConfig nqcTemplate = NamedQueryConfig.getBySys(nqName);
            String sql = nqcTemplate.getRawsql();
            for (Object object : params) {
                if (logger.isInfoEnabled())
                    logger.info("execute sql[" + nqName + "] parameters: [" + object.toString() + "]");
            }
            sql = appendOrder(sql, obe);
            return qu.executeSql(nqName, sql, params, clazz);
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 指定NameQuery的名称，获取对应的SQL，不解析该SQL，直接给SQL设置参数并执行，<br/>
     * 该方法需要注意: SQL中的参数必须和params的参数顺序，个数保持一致，并且使用占位符 ? 作为参数
     * <br/><br/>
     * 建议在SQL解析器无法解析SQL语句时，才用该方法
     *
     * @param nqName
     * @param params 参数列表
     */
    public List<Object[]> findFinalByNq(String nqName, List<Object> params, OrderByElement obe) throws RuntimeException {
        try {
            QueryUtil qu = getSqlExeInstance(nqName);
            NamedQueryConfig nqcTemplate = NamedQueryConfig.getBySys(nqName);
            String sql = nqcTemplate.getRawsql();
            for (Object object : params) {
                if (logger.isInfoEnabled())
                    logger.info("execute sql[" + nqName + "] parameters: [" + object.toString() + "]");
            }
            sql = appendOrder(sql, obe);
            return qu.executeSql(nqName, sql, params);
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    private String appendOrder(String sql, OrderByElement obe) {
        if (obe != null) {
            if (obe.getOrderMap() != null && !obe.getOrderMap().isEmpty()) {//如果getOrderMap不为空，则以该参数来构造SQL
                LinkedHashMap<String, String> orderMap = obe.getOrderMap();
                StringBuilder orderBySql = new StringBuilder(" ORDER BY ");
                boolean hasOrder = false;
                for (Iterator it = orderMap.keySet().iterator(); it.hasNext(); ) {
                    Object key = it.next();
                    orderBySql.append(key).append(" ").append(orderMap.get(key)).append(",");
                    hasOrder = true;
                }
                if (hasOrder)
                    return sql + " " + orderBySql.substring(0, orderBySql.length() - 1);
                else
                    return sql;
            } else {
                String[] field = obe.getOrderByField();
                if (field != null && field.length > 0) {
                    //String orderBySql = " ORDER BY ";
                    StringBuilder orderBySql = new StringBuilder(" ORDER BY ");
                    boolean hasOrder = false;
                    for (int i = 0; i < field.length; i++) {
                        if (StringUtils.isEmpty(field[i]))
                            continue;
                        else {
                            orderBySql.append(field[i]).append(" ").append(obe.getOrderByType()).append(",");
                            hasOrder = true;
                        }
                    }
                    if (hasOrder)
                        return sql + " " + orderBySql.substring(0, orderBySql.length() - 1);
                    else
                        return sql;
                } else
                    return sql;
            }
        } else
            return sql;
    }

    /**
     * 自定义SQL获取查询结果数量
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("rawtypes")
    public int getCountBySql(String sql, Map<String, Object> params) throws RuntimeException {
        try {
            if (params == null) {
                return executeQueryCount(sql, null);
            }
            NamedQueryConfig nqc = new NamedQueryConfig("", sql, "", 0);
            NativeQuery<Entry> nQuery = nqc.createNativeQuery(Entry.class);
            nQuery.setParameterMap(params);
            Entry<String, List<Object>> parse = nqc.getSqlAndParams(nQuery);
            return executeQueryCount(parse.getKey(), parse.getValue());
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 指定NameQuery的名称查询结果数量，不解析该SQL，直接给SQL设置参数并执行，<br/>
     * 该方法需要注意: SQL中的参数必须和params的参数顺序，个数保持一致，并且使用占位符 ? 作为参数
     * <br/><br/>
     * 建议在SQL解析器无法解析SQL语句时，才用该方法
     *
     * @param nqName
     * @param params
     * @return count
     */
    public int getCountByFinalNq(String nqName, List<Object> params) throws RuntimeException {
        try {
            QueryUtil qu = getSqlExeInstance(nqName);
            NamedQueryConfig nqcTemplate = NamedQueryConfig.getBySys(nqName);
            String sql = nqcTemplate.getRawsql();
            for (Object object : params) {
                if (logger.isInfoEnabled())
                    logger.info("execute sql[" + nqName + "] parameters: [" + object.toString() + "]");
            }
            return qu.executeQueryCount(sql, params);
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据命名查询获取查询结果数量
     *
     * @param nqName
     * @param params
     * @return -1=查询出错， 0=无结果 >0=有结果
     */
    public int getCount(String nqName, Map<String, Object> params) throws RuntimeException {
        try {
            QueryUtil qu = getSqlExeInstance(nqName);
            Entry<String, List<Object>> parse = SQLPretreatment.createEntry(nqName, params);
            return qu.executeQueryCount(nqName, parse.getKey(), parse.getValue());
        } catch (SQLException e) {
            logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    private int executeQueryCount(String sql, List<Object> params) throws SQLException {
        return executeQueryCount(null, sql, params);
    }

    /**
     * 执行查询count的sql
     *
     * @param sql
     * @param params
     * @return
     */
    private int executeQueryCount(String nqName, String sql, List<Object> params) throws SQLException {
        sql = "select count(*) from (" + sql + ")";
        if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + ": " + sql);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int resultCount = 0;
        try {
            long start = System.currentTimeMillis();
            conn = getConn();
            ps = conn.prepareStatement(sql);
            putParamToSql(ps, params);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultCount = rs.getInt(1);
            }
            long end = System.currentTimeMillis();
            long cost = end - start;
            if (cost > 1000)
                logger.warn("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], get count[" + resultCount + "]");
            else if (logger.isInfoEnabled())
                logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], get count[" + resultCount + "]");
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        return resultCount;
    }

    /**
     * 生成分页查询sql
     *
     * @param sql
     * @param currentPage
     * @param countPerPage
     * @return
     */
    private String createPageSql(String sql, int currentPage, int countPerPage) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ppnq_.* FROM (SELECT oonq_.*, rownum AS row_num FROM ( ")
                .append(sql).append(" ) oonq_ where rownum < ")
                .append(((currentPage + 1) * countPerPage + 1))
                .append(" ) ppnq_  WHERE ppnq_.row_num > ")
                .append(currentPage * countPerPage);
        return sb.toString();
    }

    /**
     * 返回Object[] 列表对象的SQL查询
     *
     * @param sql
     * @param params
     * @return
     */
    private List<Object[]> executeSql(String sql, List<Object> params) throws SQLException {
        return executeSql(null, sql, params);
    }

    /**
     * 返回Object[] 列表对象的SQL查询
     *
     * @param sql
     * @param params
     * @return
     */
    private List<Object[]> executeSql(String nqName, String sql, List<Object> params) throws SQLException {
        if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + ": " + sql);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object[]> returnResult = new ArrayList<Object[]>();
        try {
            //conn = dataSource.getConnection();
            conn = getConn();
            ps = conn.prepareStatement(sql);
            putParamToSql(ps, params);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                Object[] obj = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    obj[i] = rs.getObject(i + 1);
                }
                returnResult.add(obj);
            }
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        return returnResult;
    }

    private <T> List<T> executeSql(String sql, List<Object> params, Class<T> clazz) throws SQLException {
        return executeSql(null, sql, params, clazz);
    }

    /**
     * 执行SQL,并返回指定对象类型的结果集列表
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    private <T> List<T> executeSql(String nqName, String sql, List<Object> params, Class<T> clazz) throws SQLException {
        if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + ": " + sql);
        List<T> list = new ArrayList<T>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            long start = System.currentTimeMillis();
            //conn = dataSource.getConnection();
            conn = getConn();
            ps = conn.prepareStatement(sql);
            putParamToSql(ps, params);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int column = rsmd.getColumnCount();
            long end = System.currentTimeMillis();
            long cost = end - start;
            //Field[] fs = clazz.getDeclaredFields();
            Field[] fs = getDeclaredField(clazz);
            Map<String, Field> fieldMap = new HashMap<String, Field>();
            for (Field field : fs) {
                fieldMap.put(field.getName().toUpperCase(), field);
            }
            while (rs.next()) {
                try {
                    T instance = clazz.newInstance();
                    for (int i = 1; i <= column; i++) {
                        String rsField = rsmd.getColumnLabel(i);
                        if (fieldMap.get(rsField) != null) {
                            Field field = fieldMap.get(rsField);
                            Type tp = field.getGenericType();
                            String fieldName = field.getName().substring(0, 1)
                                    .toUpperCase()
                                    + field.getName().substring(1);
                            // 如果以后还有什么其它的 类型，可以在这里补充
                            if (tp == String.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, String.class);
                                m.invoke(instance, rs.getString(rsField));
                            } else if (tp == Integer.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, Integer.class);
                                m.invoke(instance, rs.getInt(rsField));
                            } else if (tp == int.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, int.class);
                                m.invoke(instance, rs.getInt(rsField));
                            } else if (tp == Long.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, Long.class);
                                m.invoke(instance, rs.getLong(rsField));
                            } else if (tp == long.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, long.class);
                                m.invoke(instance, rs.getLong(rsField));
                            } else if (tp == Date.class) { // 这里Date是java.dbutil.tools.Date
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, Date.class);
                                m.invoke(instance, (Date) rs.getObject(rsField)); // 使用getObject不会丢失时，分，秒的信息
                            } else if (tp == Timestamp.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, Timestamp.class);
                                m.invoke(instance, (Date) rs.getTimestamp(rsField));
                            } else if (tp == Float.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, Float.class);
                                m.invoke(instance, rs.getFloat(rsField));
                            } else if (tp == float.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, float.class);
                                m.invoke(instance, rs.getFloat(rsField));
                            } else if (tp == Double.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, Double.class);
                                m.invoke(instance, rs.getDouble(rsField));
                            } else if (tp == double.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, double.class);
                                m.invoke(instance, rs.getDouble(rsField));
                            } else if (tp == Boolean.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, Boolean.class);
                                m.invoke(instance, rs.getBoolean(rsField));
                            } else if (tp == boolean.class) {
                                Method m = instance.getClass().getMethod(
                                        "set" + fieldName, boolean.class);
                                m.invoke(instance, rs.getBoolean(rsField));
                            } else {
                                logger.warn("未识别字段类型【" + tp + "】字段值【"
                                        + rs.getObject(rsField) + "】");
                            }
                        }
                    }
                    list.add(instance);
                } catch (NoSuchMethodException e) {
                    logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
                } catch (InstantiationException e) {
                    logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
                } catch (IllegalAccessException e) {
                    logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
                } catch (InvocationTargetException e) {
                    logger.error(org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e));
                }
            }
            fieldMap = null;
            long end2 = System.currentTimeMillis();
            if (cost > 1000)
                logger.warn("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], convert to List<" + clazz.getName() + ">  cost[" + (end2 - end) + "ms], get result [" + list.size() + "] rows.");
            else if (logger.isInfoEnabled())
                logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], convert to List<" + clazz.getName() + ">  cost[" + (end2 - end) + "ms], get result [" + list.size() + "] rows.");
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        return list;
    }

    /**
     * 获取java类定义的公共，默认，保护，私有，父类的所有字段属性
     *
     * @param clazz
     * @return
     */
    private static Field[] getDeclaredField(Class<?> clazz) {
        Map<String, String> tmpMap = new HashMap<String, String>();
        List<Field> tmpList = new ArrayList<Field>();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] cuField = clazz.getDeclaredFields();
                for (Field field2 : cuField) {
                    if (tmpMap.get(field2.getName()) == null) {
                        tmpMap.put(field2.getName(), field2.getName());
                        tmpList.add(field2);
                    }
                }
            } catch (Exception e) {
            }
        }
        Field[] field = tmpList.toArray(new Field[tmpList.size()]);
        tmpMap = null;
        tmpList = null;
        return field;
    }


    /**
     * 执行查询count的sql的hibernate实现
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    @SuppressWarnings({"unchecked"})
    private int executeHQueryCount(String nqName, String sql, List<Object> params) throws RuntimeException {
        int resultCount = 0;
        sql = "select count(*) from (" + sql + ")";
        if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + ": " + sql);
        SQLQuery query = getSession().createSQLQuery(sql);
        for (int i = 0; params != null && i < params.size(); i++) {
            query.setParameter(i, params.get(i));
        }
        List<Object[]> countObj = query.list();
        resultCount = Integer.parseInt(String.valueOf(countObj.get(0)));
        if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " get count[" + resultCount + "]");
        return resultCount;
    }

    /**
     * 返回指定结果集的Hibernate实现
     *
     * @param sql
     * @param params
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> List<T> executeHSql(String nqName, String sql, List<Object> params, Class<T> clazz) {
        if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + ": " + sql);
        long start = System.currentTimeMillis();
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setResultTransformer(new EscColumnToBean(clazz));
        for (int i = 0; params != null && i < params.size(); i++) {
            query.setParameter(i, params.get(i));
        }
        List<T> temp = query.list();
        long end = System.currentTimeMillis();
        long cost = end - start;
        if (cost > 1000)
            logger.warn("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], get result [" + temp.size() + "] rows.");
        else if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], get result [" + temp.size() + "] rows.");
        return temp;
    }

    /**
     * 返回Object结果集的Hibernate实现
     *
     * @param sql
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Object[]> executeHSql(String nqName, String sql, List<Object> params) {
        if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + ": " + sql);
        long start = System.currentTimeMillis();
        SQLQuery query = getSession().createSQLQuery(sql);
        for (int i = 0; params != null && i < params.size(); i++) {
            query.setParameter(i, params.get(i));
        }
        List<Object[]> temp = query.list();
        long end = System.currentTimeMillis();
        long cost = end - start;
        if (cost > 1000)
            logger.warn("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], get result [" + temp.size() + "] rows.");
        else if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], get result [" + temp.size() + "] rows.");
        return temp;
    }

    public Connection getConnection() {
        try {
            return getSessionFactory().getCurrentSession().connection();
        } catch (Exception e) {
            return getSessionFactory().openSession().connection();
        }
    }

    /**
     * 将之前Hibernate做的持久化操作更新到数据库事务缓存中
     */
    private void flush() {
        getSession().flush();
        //getSession().clear();
    }

    private Connection getConn() {
        flush();
        return getSession().connection();
    }
}

