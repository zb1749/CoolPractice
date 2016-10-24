package multithread.threadpool;

/**
 * 线程池类型
 */
public class ThreadPoolType {
	public static final String COMMON_THREAD_POOL = "com.cmread.cmu.portal.web.util.threadpool.ManagerThreadPool";
	public static final String BACKGROUND_TASK_THREAD_POOL = "com.cmread.cmu.portal.web.util.threadpool.BackgroundTaskExcuteTheadPool";
	public static final String USERGROUP_SYN_TASK_THREAD_POOL = "com.cmread.cmu.portal.web.util.threadpool.UserGroupSyncThreadPool";
	public static final String CHANNEL_PLAN_THREAD_POOL = "com.cmread.cmu.portal.web.util.threadpool.ChannelPlanThreadPool";
	public static final String NOTIFY_TONSS_THREAD_POOL = "com.cmread.cmu.portal.web.util.threadpool.NotifyToNssThreadPool";
	public static final String BATCH_IMPORT_ONOFF_SHELF_POOL_NAME = "com.cmread.cmu.portal.web.task.contentplan.BatchShelfTask";
	public static final String DEAL_PACKAGE_TASK_POOL = "com.cmread.cmu.portal.web.util.threadpool.DealPackageTaskPool";
	public static final String STRATEGY_THREAD_POOL = "com.cmread.cmu.portal.web.task.quartz.thread.StrategyThread";
	public static final String CIDGROUP_IMPORT_TASK_THREAD_POOL = "com.cmread.cmu.portal.web.task.quartz.thread.CidGroupImportThread";
	public static final String BDC_HANDLER_TASK_THREAD_POOL = "com.cmread.cmu.portal.web.quartz.cdrlog.BdcHandlerThreadPool";
	public static final String HOTBOOK_PACKAGE_SYN_TASK_THREAD_POOL = "com.cmread.cmu.portal.web.util.threadpool.HotBookPackageThreadPool";
	public static final String BATCHSUBSCRIBE_THREAD_POOL = "com.cmread.cmu.portal.web.util.threadpool.BatchSubscribeThreadPool";
	public static final String CONT_SYNC_THREAD_POOL = "com.cmread.cmu.portal.web.util.threadpool.ContSyncThreadPool";
	public static final String SYNC_DASPARK_THEAD_POOL = "com.cmread.cmu.portal.web.util.threadpool.SyncDasparkBooklistThreadPool";
    public static final String BOOKTOCHANNAL_THREAD_POOL = "com.cmread.cmu.portal.web.util.threadpool.BookToChannalThreadPool";
    public static final String SEARCHCDLOG_TASK_THREAD_POOL="com.cmread.cmu.portal.web.util.threadpool.SearchCDLogUploadThreadPool";
    
}
