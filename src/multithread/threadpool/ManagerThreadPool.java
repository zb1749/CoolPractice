package multithread.threadpool;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;


/**
 * Manager线程池管理器，线程池都放入HashMap静态变量REGISTER_MAP中进行管理，保证每种线程只有一个实例存在
 * 如果有线程需要执行，扔进相应的线程池即可
 */
public class ManagerThreadPool {

    private static final Logger logger = Logger
            .getLogger(ManagerThreadPool.class);
    // 保存线程池的hashmap，保证每种线程池只有一个存在
    private static HashMap<String, ManagerThreadPool> REGISTER_MAP = new HashMap<String, ManagerThreadPool>();

    // 后台任务处理线程等待队列长度
    public static final String BGTASK_THREAD_QUEUE_COUNT = "common.backgroundTaskMng-threadpool.queuecount";

    // 处理线程最小值，默认为4
    protected int corePoolSize = 4;

    // 处理线程最大值，默认为8
    protected int maximumPoolSize = 16;

    // 线程池维护线程所允许的空闲时间
    protected long keepAliveTime = 10;

    // 线程池维护线程所允许的空闲时间的单位
    protected TimeUnit unit = TimeUnit.SECONDS;

    // 线程池所使用的缓冲队列
    protected BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(
            Integer.parseInt(ConfigManageUtil
                    .getCommonProperties(BGTASK_THREAD_QUEUE_COUNT)));
    // 线程池对拒绝任务的处理策略：AbortPolicy为抛出异常；CallerRunsPolicy为重试添加当前的任务，他会自动重复调用execute()方法；DiscardOldestPolicy为抛弃旧的任务，DiscardPolicy为抛弃当前的任务
    protected CallerRunsPolicy handler = new CallerRunsPolicy();

    // 线程池
    protected ThreadPoolExecutor POOL = null;

    /**
     * 定义为保护，不允外部许初始化
     */
    protected ManagerThreadPool() throws RuntimeException {
        logger.info("Initializing Manager thread pool now......");

        // 暂时从常量类中去名称，后续需改造成从配置文件读取各参数
        // TODO
        corePoolSize = 8;

        maximumPoolSize = 16;

        keepAliveTime = 10;

        unit = TimeUnit.SECONDS;

        workQueue = new ArrayBlockingQueue<Runnable>(10);

        handler = new CallerRunsPolicy();

        // 创建一个线程池
        createThreadPool();

        logger.info("Initializing Manager thread pool successfully!");

    }

    /**
     * 创建线程池
     */
    protected void createThreadPool() throws RuntimeException {
        try {
            POOL = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                    keepAliveTime, unit, workQueue, handler);
            logger.info("Manager thread pool initialized successfully!");
        } catch (Exception e) {
            logger.fatal(
                    "Manager thread pool initialized fail!Please restart!", e);
            throw new RuntimeException("");
        }
    }

    protected static ManagerThreadPool getInstance() throws RuntimeException {
        return getInstance(null);
    }

    /**
     * 根据线程池名称获取线程池的单实例
     */
    protected static ManagerThreadPool getInstance(String name)
            throws RuntimeException {
        // 如果name为空，则取得默认的线程池
        if (null == name || "".equals(name.trim())) {
            // 暂时从常量类中去名称，后续需改造成从配置文件读取
            name = ThreadPoolType.COMMON_THREAD_POOL;
        }

        // 如果没有从map中取得实例，说明还未被初始化，进行实例化
        if (null == REGISTER_MAP.get(name)) {
            logger.info("Thread pool " + name
                    + " has not been initialized,so initialize it now");

            try {
                // 向HaspMap中中添加新类型的线程池
                REGISTER_MAP.put(name, (ManagerThreadPool) Class.forName(name)
                        .newInstance());
            } catch (Exception e) {
                logger.fatal("Initializd thread pool " + name
                        + " failed,it's fatal", e);
                throw new RuntimeException("Initializd thread pool " + name
                        + " failed");
            }

        }

        logger.info("Thread pool number is " + REGISTER_MAP.size());

        // 从hashmap中获取线程池
        return (ManagerThreadPool) REGISTER_MAP.get(name);

    }

    /**
     * 对外提供的接口，向线程池中添加线程任务
     *
     * @param name 线程池类全名
     * @param task 线程任务
     */
    public static void addTask(String name, Runnable task)
            throws RuntimeException {
        logger.info("add task into " + name + " thread pool");

        // 根据线程池类型将任务添加到相应的线程池中
        ManagerThreadPool.getInstance(name).add(task);
    }

    /**
     * 对外提供的接口，向默认线程池添加任务
     */
    public static void addTask(Runnable task) throws RuntimeException {
        logger.info("add task into default thread pool");
        ManagerThreadPool.getInstance("").add(task);
    }

    /**
     * 向线程池中添加执行任务，也就是添加要执行的线程
     */
    private void add(Runnable task) {
        try {
            // 将任务扔入线程池中
            POOL.execute(task);
        } catch (Exception e) {
            logger.error("", e);
        }

    }

    /**
     * 获取线程池对象
     */
    public ThreadPoolExecutor getPOOL() {
        return POOL;
    }

    /**
     * 获取线程池列表
     */
    public static HashMap<String, ManagerThreadPool> getPoolMap() {
        return REGISTER_MAP;
    }

}
