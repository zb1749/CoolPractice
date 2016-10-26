package dbutil;


import dbutil.portal.BaseUtil;
import dbutil.common.Entry;
import dbutil.common.ExecuteSqlObjCache;
import dbutil.portal.SQLPretreatment;
import dbutil.queryconfig.nq.NamedQueryConfig;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL执行类，用于增，删，改操作
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class BaseExecuteUtil extends HibernateDaoSupport implements BaseUtil, ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = Logger.getLogger(BaseExecuteUtil.class);

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

    public static BaseExecuteUtil getInstance() {
        //修改成applicationContext读取bean方式，用于junit测试
        //WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

        //return  (BaseExecuteUtil) applicationContext.getBean("baseExecuteUtil");

        if (ExecuteSqlObjCache.esCache.size() == 0) {
            ExecuteSqlObjCache esc = (ExecuteSqlObjCache) applicationContext.getBean("executeSqlObjCache");
            esc.init();
        }
        return ExecuteSqlObjCache.esCache.get(SQLPretreatment.defaultDataSourceName);
    }

    public static BaseExecuteUtil getInstance(String sessionFactoryName) {
        //修改成applicationContext读取bean方式，用于junit测试
        //WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

        if (ExecuteSqlObjCache.esCache.size() == 0) {
            ExecuteSqlObjCache esc = (ExecuteSqlObjCache) applicationContext.getBean("executeSqlObjCache");
            esc.init();
        }
        return ExecuteSqlObjCache.esCache.get(sessionFactoryName);
    }

    public BaseExecuteUtil getSqlExeInstance(String nqName) {
        //从系统中取出SQL模板
        NamedQueryConfig nqcTemplate = NamedQueryConfig.getBySys(nqName);
        String sfName = nqcTemplate.getTag();
        if (sfName == null || "".equals(sfName))
            sfName = SQLPretreatment.defaultDataSourceName;
        else
            logger.info("用户自定义数据源:" + sfName);
        return ExecuteSqlObjCache.esCache.get(sfName);
    }

    /**
     * 指定NameQuery的名称，获取对应的SQL，不解析该SQL，直接给SQL设置参数并执行，
     * 该方法需要注意，SQL中的参数必须和params的参数顺序，个数保持一致，并且使用占位符 ? 作为参数
     * 建议在SQL解析器无法解析SQL语句时，才用该方法
     *
     * @param nqName
     * @param params 参数列表
     * @throws SQLException
     */
    public void executeFinalNq(String nqName, List<Object> params) throws SQLException {
        BaseExecuteUtil qu = getSqlExeInstance(nqName);
        NamedQueryConfig nqcTemplate = NamedQueryConfig.getBySys(nqName);
        String sql = nqcTemplate.getRawsql();
        executeSql(nqName, sql, params, qu);
    }

    /**
     * 执行NameQuery
     *
     * @param nqName
     * @param params
     * @throws SQLException
     */
    public int executeNq(String nqName, Map<String, Object> params) throws SQLException {
        BaseExecuteUtil qu = getSqlExeInstance(nqName);
        Entry<String, List<Object>> parse = SQLPretreatment.createEntry(nqName, params);
        String sql = parse.getKey();
        List<Object> paramObj = parse.getValue();
        return executeSql(nqName, sql, paramObj, qu);
    }

    /**
     * 批量执行SQL
     *
     * @param nqName
     * @param params
     * @return
     * @throws SQLException
     */
    public int executeBatchNq(String nqName, List<Map<String, Object>> params) throws SQLException {
        BaseExecuteUtil qu = getSqlExeInstance(nqName);
        List<Entry<String, List<Object>>> parseList = new ArrayList<Entry<String, List<Object>>>();
        for (Map<String, Object> map : params) {
            Entry<String, List<Object>> parse = SQLPretreatment.createEntry(nqName, map);
            parseList.add(parse);
        }
        if (parseList.size() > 0)
            return executeBatchSql(nqName, parseList, qu);
        else
            return 0;
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
                ps.setNull(i + 1, java.sql.Types.NULL);
            } else {
                if (obj instanceof java.sql.Date)
                    ps.setDate(i + 1, (java.sql.Date) obj);
                else if (obj instanceof java.sql.Timestamp)
                    ps.setTimestamp(i + 1, (java.sql.Timestamp) obj);
                else
                    ps.setObject(i + 1, params.get(i));
            }
        }
    }

    /**
     * 执行一条sql
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int executeHSql(String nqName, String sql, List<Object> params, BaseExecuteUtil qu) throws SQLException {
        flush(qu);
        long start = System.currentTimeMillis();
        SQLQuery exeQuery = getSession().createSQLQuery(sql);
        if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + ": " + sql);
        for (int i = 0; params != null && i < params.size(); i++) {
            exeQuery.setParameter(i, params.get(i));
        }
        int result = exeQuery.executeUpdate();
        long end = System.currentTimeMillis();
        long cost = end - start;
        if (cost > 1000)
            logger.warn("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], influenced [" + result + "] rows.");
        else if (logger.isInfoEnabled())
            logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], influenced [" + result + "] rows.");
        return result;
    }

    public int executeSql(String sql, List<Object> params) throws SQLException {
        return executeSql(null, sql, params, this);
    }

    /**
     * 执行一条sql
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int executeSql(String nqName, String sql, List<Object> params, BaseExecuteUtil qu) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = getConn(qu);
            ps = conn.prepareStatement(sql);
            putParamToSql(ps, params);
            if (logger.isInfoEnabled())
                logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + ": " + sql);
            int result = ps.executeUpdate();
            long end = System.currentTimeMillis();
            long cost = end - start;
            if (cost > 1000)
                logger.warn("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], influenced [" + result + "] rows.");
            else if (logger.isInfoEnabled())
                logger.info("execute sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], influenced [" + result + "] rows.");
            return result;

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
    }

    /**
     * 批量执行SQL
     *
     * @param parseList
     * @return
     * @throws SQLException
     */
    private int executeBatchSql(String nqName, List<Entry<String, List<Object>>> parseList, BaseExecuteUtil qu) throws SQLException {
        if (parseList.size() == 0)
            return 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = getConn(qu);
            String sql = parseList.get(0).getKey();
            if (logger.isInfoEnabled())
                logger.info("execute Batch Sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + ": " + sql);
            ps = conn.prepareStatement(sql);
            int exeNum = 0;
            //int commitNum = 0;
            for (Entry<String, List<Object>> entry : parseList) {
                exeNum++;
                List<Object> params = entry.getValue();
                putParamToSql(ps, params);
                ps.addBatch();
            }
            ps.executeBatch();
            long end = System.currentTimeMillis();
            long cost = end - start;
            if (cost > 5000)
                logger.warn("execute Batch Sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], influenced [" + exeNum + "] rows.");
            else if (logger.isInfoEnabled())
                logger.info("execute Batch Sql" + ((nqName == null || "".equals(nqName)) ? "" : "[" + nqName + "]") + " cost[" + cost + "ms], influenced [" + exeNum + "] rows.");
            return parseList.size();
        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
    }


    /**
     * 将之前Hibernate做的持久化操作更新到数据库事务缓存中,清除hibsernate的session缓存
     */
    private void flush(BaseExecuteUtil qu) {
        qu.getSession().flush();
        qu.getSession().clear();
    }

    private Connection getConn(BaseExecuteUtil qu) {
        flush(qu);
        return qu.getSession().connection();
    }
}

