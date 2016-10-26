package dbutil.common;


import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import dbutil.BaseExecuteUtil;
import dbutil.QueryUtil;

public class ExecuteSqlObjCache implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = Logger.getLogger(ExecuteSqlObjCache.class);

    private ApplicationContext applicationContext = null;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (this.applicationContext == null) {
            this.applicationContext = event.getApplicationContext();
        }
    }

    /**
     * 将QueryUtil对象缓存，缓存的key为sessionFactory的hashCode
     */
    public static Map<String, QueryUtil> queryCache = new HashMap<String, QueryUtil>();
    /**
     * 将BaseExecuteUtil对象缓存，缓存的key为sessionFactory的hashCode
     */
    public static Map<String, BaseExecuteUtil> esCache = new HashMap<String, BaseExecuteUtil>();

    public Map<String, SessionFactory> sfMap;

    public Map<String, SessionFactory> getSfMap() {
        return sfMap;
    }

    public void setSfMap(Map<String, SessionFactory> sfMap) {
        this.sfMap = sfMap;
    }

    public synchronized void init() {
        for (Map.Entry<String, SessionFactory> entry : sfMap.entrySet()) {
            logger.info("put sessionfactory[" + entry.getKey() + "]" + entry.getValue().toString());

            QueryUtil qu = (QueryUtil) applicationContext.getBean("queryUtil");
            qu.setSessionFactory(entry.getValue());
            queryCache.put(entry.getKey(), qu);

            BaseExecuteUtil be = (BaseExecuteUtil) applicationContext
                    .getBean("baseExecuteUtil");
            be.setSessionFactory(entry.getValue());
            esCache.put(entry.getKey(), be);
        }
    }


}
