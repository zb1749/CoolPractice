package multithread.threadpool;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

/**
 * 工程配置信息
 * 
 * @author huangliangliang
 * 
 */
public class ConfigManageUtil {
	/**
	 * 读取NSS异步处理结果的Servlet
	 */
	public static String SyncBIChannelInfoServletNotifyUrl;
	
	/**
	 * 发送二级渠道变更通知
	 */
	public static String secondChannelChangeUrl = "http://localhost/bks/packBooksOfAuthorAgain";
	
	/**
	 * 网页SDK地址
	 */
	public static String wapsdkGetNetidUrl = "http://localhost/bks/packBooksOfAuthorAgain";
	public static String wapsdkGetNetidUrlCtype;
	/**
	 * 网页SDK注册结果查询地址
	 */
	public static String wapsdkGetNetidRegisterUrl;
	
	/**
	 * 支付宝生活账号管理
	 */
	public static String zfbLiveNumMngCoverSaveDir;
	public static String zfbLiveNumMngCatalogId;
	public static String zfbLiveNumMngContactTel;
	public static String zfbLiveNumMngContactEmail;
	
	
	/* 离线制作工具保存路径 */
	public static String EPUBMAKERUPLOADPATH;
	/* 作家信息管理-导出查询结果-临时文件路径 */
	public static String authorInfoMngExportPath;
	// 运营管理-SDK加固管理
	public static String control_platform_http;// 处理http请求
	public static String control_platform_userName;// ftp用户名
	public static String control_platform_passWord;// ftp密码
	public static String control_platform_ip;// ftp服务器ip
	public static String control_platform_port;// ftp端口
	public static String control_platform_remotePath;
	public static String control_platform_shellpath;
	// 计费文件请求的Http地址
	public static String send_to_group_url;
	// 计费之后的文件路径../Books/
	public static String charge_later_filepath;
	// 计费之前的文件路径../Books
	public static String charge_before_filepath;
	// 上传未加密计费XML路径
	public static String xmluploadpath;
	// <receivePath>../Books/downloadSdkXmlFtp/</receivePath>
	public static String charging_receivePath;
	// <receiveSavePath>../Books/downloadSdkXml/</receiveSavePath>
	public static String charging_receiveSavePath;
	/* 运营管理 -SDK加固管理-未加固SDK上传路径 */
	public static String sdkProtectMngsUploadPath;
	// ftp定时上传路径 /sdk
	public static String sdk_remotePath;
	// ../Books/DownSdkFtp/
	public static String sdk_receivePath;
	// ../Books/downSDK/
	public static String sdk_receiveSavePath;
	/* 运营管理-欢迎页管理-上传路径 */
	public static String welcomePageMgrsUploadPath;
	// 咪咕币批量上传地址 ../data/migucurrency/gift/ready
	public static String migu_uploadPath;
	/* 根据用户手机或者邮箱从aserver获取获取咪咕用户id */
	// 咪咕币赠送： 文件待处理路径 ../data/migucurrency/gift/ready/
	public static String migupoint_excelbatch_ready;
	// 咪咕币赠送 ：文件已处理路径 ../data/migucurrency/gift/done/
	public static String migupoint_excelbatch_done;
	public static String miguCoin_url;
	/* 终端管理-机型分类管理-上传路径 */
	public static String pictureTerminaListFilePath = "D:/pictureTerminaListFile";
	/**
	 * 报表sheet页最大行数, 默认5000
	 */
	public static int exportPageMaxSize = 5000;

	/** 默认临时文件存储位置 */
	public static String DEFAULT_TMP_FILE_DIR;
	/** 上传用户组号码时的临时文件存放路径 **/
	public static String USERGROUP_UPLOAD_SAVEPATH;
	/** 平台运营赠送图书上传号码文件存放路径 */
	public static String PRESENTBOOK_UPLOADPATH;
	/** 抢先版设置文件路径 */
	public static String ADVANCECONTENT_FILEPATH;
	/** 抢先版设置上传日志文件存放路径 */
	public static String ADVANCECONTENT_IMPORT_LOGFILEPATH;
	/** 用户组自动同步Ftp登陆参数 */
	public static String USERGROUP_FTP_IP;
	public static String USERGROUP_FTP_PORT;
	public static String USERGROUP_FTP_USERWORD;
	public static String USERGROUP_FTP_PASSWORD;
	public static String USERGROUP_FTP_CONNCECTMODE;
	public static String USERGROUP_FTP_LOCALPATH;
	/** CID用户组导出FTP登陆信息 */
	public static String CID_USERGROUP_BI_FTP_IP;
	public static String CID_USERGROUP_BI_FTP_PORT;
	public static String CID_USERGROUP_BI_FTP_USERNAME;
	public static String CID_USERGROUP_BI_FTP_PASSWORD;
	public static String CID_USERGROUP_LOCAL_FILE_PATH;
	public static String CID_USERGROUP_LOCAL_FILE_PATH_ZIP;
	/** 客户端消息推送CID上传文件保存路径 */
	public static String RECOMMEND_CID_UPLOAD_FILE_PATH;
	/** 用户组管理重构开关 */
	public static String USERGROUP_CGSWITCH;

	/** 中奖管理导出临时文件存储路径 */
	public static String BACKGROUND_TASK_FILEPATH;
	/** 中奖管理上传路径 */
	public static String PRIZE_FILE_SAVEPATH;
	/** 行业图书管理默认临时文件存储位置 */
	public static String default_tmp_file_dir_bk;
	/** 打赏数据结果 */
	public static String REWARD_DATA_FILEPATH;
	/** 抢先版词库管理 错误日志路径 */
	public static String badkeywordUploaderrorlogPath;

	public static String accountSimilayCheckRuleSwitch;
	public static String hasDigitCheckRuleSwitch;
	public static String hasLetterCheckRuleSwitch;
	public static String hasPunctuationCheckRuleSwitch;
	public static String lengthCheckRuleSwitch;
	public static String RULES_HPR_PUNCTUATION;
	public static String minLength;
	public static String maxLength;
	/**
	 * 上传根目录
	 */
	public static String contentUploadPath;

	/**
	 * 回收站路径
	 */
	public static String contentRecycledPath;

	/**
	 * nss接受通知地址
	 */
	public static String NssURL;
	/**
	 * nss转发server地址
	 */
	public static String serverGroupIP;
	/**
	 * RDO余额校验关停渠道导出文件路径
	 */
	public static String rdoChannelFilePath = "D:/rdoChannelInfo";
	public static String epubmakerUploadPath = "D:/download";
	public static String badInfoManagerFilePath = "E:/badInfo";
	public static String operExportFilePath = "E:/CMUFILE/operExport";
	public static String authorFilePath = "D:/author";
	public static String mcpRdoChargeInfoDocumentUrl = "../data/rdochargecodefile";
	public static String specialBookListFilePath = "D:/specialBookListFile";
	public static String prmChannelprmFilePath = "D:/pickreadmanageChannelInfo";
	public static String downloadbookBorrowFilePath = "D:/downloadbookBorrowInfo";
	public static String awardOrderFilePath = "D:/awardOrderFile";
	/**
	 * 作家收入管理-导入功能验证日志输出路径
	 */
	public static String authorProceedsLogPath = "D:/authorProceedsLog.txt";
	/**
	 * 作家收入管理-批量月票/打赏支付 向外部接口发请求url
	 */
	public static String authorProceedsPayUrl = "http://10.211.93.220:9094/snsserver/portalapi/authorPayRequest";
	/**
	 * 作家认证管理-身份证扫描件存放根路径
	 */
	public static String idCardApacheTomcatPath = "../apache-tomcat/";
	/**
	 * 作家认证管理-首次认证、已认证-发送站内信请求路径
	 */
	public static String authorApproveSendMailUrl;
	/**
	 * 作家信息管理-冻结解冻发送作家信息变更通知server
	 */
	public static String authorChangePathEnd;
	/**
	 * 作家信息管理-发送变更消息体到论坛url
	 */
	public static String forumAuthorChangeUrl;
	/**
	 * mcp资质上传文件地址
	 */
	public static String filetmpUploadPath;

	/**
	 * 营销管理 ：营销活动配置管理：团购任务配置 文件上传路径
	 */
	public static String grouponPath;
	/**
	 * mcp账号冻结最大天数
	 */
	public static int mcpAcountFreezeDaysSize = 60;
	/**
	 * mcp资质申请最多驳回次数
	 */
	public static int rejectCountSize = 2;
	/**
	 * 客户端下载图书，漫画接口并发最大并发数
	 */
	public static String downloadContentConcurrenceLimit;
	/**
	 * aserver接口 注册手机号
	 */
	public static String aserverGroupDefault;
	/**
	 * 作家信息管理-修改时发送作家信息变更延时时间
	 */
	public static String authorInfoUpdateDelayTime = "300";
	/**
	 * 
	 */
	public static String caricatureIp;
	/**
	 * 图书分级信息文件路径
	 */
	public static String bookclassfyFilePath;
	/**
	 * 图书分级信息文件行数最大限制
	 */
	public static String bookclassfyTotalRow;
	/**
	 * 续传章节免审文件路径
	 */
	public static String resumeNotReviewFilePath;
	/**
	 * 免审图书上传模板
	 */
	/* logo本地保存的路径 */
	public static String issnLogoPath;
	/* logo数据库存放路径 */
	public static String issnLogoDataPath;
	public static String resumeNotReviewTemplate;
	public static String searchCDLog;
	/**
	 * 热门书目信息文件行数最大限制
	 */
	public static String hotBookitemTotalRow;
	/**
	 * 微阅读书单导入路径
	 */
	public static String vbookFilePath;
	/**
	 * 微阅读导入书单后，同步话单延迟时间
	 */
	public static String vbookSyncDelayTime;
	/**
	 * 业务类型配置项路径
	 */
	public static String BDCServtype;

	/**
	 * 作家名下图书重新打包Servlet URL
	 */
	public static String packBooksOfAuthorAgainServletUrl = "http://localhost/bks/packBooksOfAuthorAgain";

	/**
	 * 删除推荐单元成员Servlet URL
	 */
	public static String deleteRecommendUnitMemberServletUrl = "http://localhost/bks/deleteRecommendUnitMemberServlet";

	/**
	 * 查询我的待办事项servlet url
	 */
	public static String queryMyWorkListUrl;

	/** 版权冻结调用图书下架地址 */
	public static String COPYRIGHT_FREEZE_AND_BOOK_OFF_SHELF_URL;
	/** 版权解冻调用接口地址 */
	public static String COPYRIGHT_THAW_URL;
	/** 续期版权审核通过自动上架图书开关 */
	public static String COPYRIGHT_AUTO_SHELVES_SWITCH;

	/** 版权文件地址 */
	public static String COPYRIGHT_FILE_PATH;

	/**
	 * 版权文件大小限制
	 */
	public static String COPYRIGHT_FILE_SIZE;

	/**
	 * bks版权库维护批量新增日志路径
	 */
	public static String COPYRIGHTLIBMNG_BATCHCREATE;

	/**
	 * bks版权库维护批量更新日志路径
	 */
	public static String COPYRIGHTLIBMNG_BATCHUPDATE;

	/**
	 * 知识库设置文件值
	 */
	public static String KNOWLEDGE_BASE_FILE_SIZE;

	public static String knowledgeFileRootPath;

	public static String myWorkFilePath;

	public static String CMU_Server_IP;

	public static String hotBookitemFile;
	public static String hotbook;

	public static String mobileskinuploadpath;

	public static String mobileindustryuploadpath;

	public static String mobileindustrybookuploadpath;

	public static String industrybookloadpath;

	public static String seriesbookbanquanpath;

	public static String industrybookvideopath;

	/**
	 * 导出任务
	 */
	public static String UserCommentExport;
	public static String BookLibraryExport;
	public static String UserGroupExport;
	/** 登录图片路径 */
	public static String UPLOAD_IMG;
	/** DRM服务地址 IP */
	public static String DRMADDRESS_PREEIP;
	/** DRM服务地址 PATH */
	public static String DRMADDRESS_PATH;

	/** 企业信息管理模板文件 */
	public static String ENTERPRISEINFO;

	/**
	 * 软件类型名字
	 * (Java|Symbian|WindowsMobile|WinCE|OMS|Palm|BlackBerry|Linux|Iphone|
	 * Android|Ipad)
	 */
	public static String softwareTypeName;
	/** 软件类型值 (1|2|3|4|5|6|7|8|9|10|11) */
	public static String softwareTypeValue;

	/** 排行榜黑名单 上传结果保存文件路径 */
	public static String RANK_UPLOAD_RESULT_PATH;
	/**
	 * 内容品质管理文件路径
	 */
	public static String CONTENTQUALITY_FILE_PATH;

	/** 包月批量订购/退订任务每批需要处理的数据条数 */
	public static String PAYMONTH_TASK_BATCH_COUNT;
	/** 灰度引擎配置项 */
	public static String GRAYQUERY;
	/** 文件服务器根目录 */
	public static String sysPath;
	/**
	 * 活动申请单配置开关
	 */
	public static String PROMOTION_APPLY_SWITCH;

	/**
	 * 与一级平台约定密钥
	 */
	public static String secretkey;

	public static String firstLevle_ftp_localPath;

	public static String firstLevle_ftp_ip;

	public static String firstLevle_ftp_port;

	public static String firstLevle_ftp_userword;

	public static String firstLevle_ftp_password;

	public static String firstLevle_conncectMode;

	public static String firstLevle_uploadbin;

	/**
	 * 与漫画平台约定秘钥
	 */
	public static String secretkey_of_comic;
	/**
	 * 与杂志平台约定秘钥
	 */
	public static String secretkey_of_magazine;
	/**
	 * 活动、产品申请单中的附件上传路径
	 */
	public static String attachmentPath;

	/*** 客户端应用加固加密 begin */
	/** ***第三方客户端 APK加固begin */
	/** 阳光计划V1.0 */
	public static String thirdSun10SDKftpIp;
	public static String thirdSun10SDKftpUserName;
	public static String thirdSun10SDKftpPassword;
	public static String thirdSun10SDKftpPort;
	public static String thirdSun10SDKftpPath;
	public static String thirdSun10SDKHttpUrl;
	/** 阳光计划V1.5 */
	public static String thirdSun15SDKftpIp;
	public static String thirdSun15SDKftpUserName;
	public static String thirdSun15SDKftpPassword;
	public static String thirdSun15SDKftpPort;
	public static String thirdSun15SDKftpPath;
	public static String thirdSun15SDKHttpUrl;
	/** 阳光计划V2.0 */
	public static String thirdSun20SDKftpIp;
	public static String thirdSun20SDKftpUserName;
	public static String thirdSun20SDKftpPassword;
	public static String thirdSun20SDKftpPort;
	public static String thirdSun20SDKftpPath;
	public static String thirdSun20SDKHttpUrl;//针对第五套
	public static String thirdSun20SDKHttpUrlForSix;//针对第六套
	/** ***第三方客户端 APK加固end */

	/** ***自有客户端 APK加固 begin */
	/** ******阳光计划V1.0 */
	public static String ownSun10SDKftpIp;
	public static String ownSun10SDKftpUserName;
	public static String ownSun10SDKftpPassword;
	public static String ownSun10SDKftpPort;
	public static String ownSun10SDKftpPath;
	public static String ownSun10SDKHttpUrl;

	/** ******阳光计划V1.5 */
	public static String ownSun15SDKftpIp;
	public static String ownSun15SDKftpUserName;
	public static String ownSun15SDKftpPassword;
	public static String ownSun15SDKftpPort;
	public static String ownSun15SDKftpPath;
	public static String ownSun15SDKHttpUrl;

	/** ******阳光计划V2.0 */
	public static String ownSun20SDKftpIp;
	public static String ownSun20SDKftpUserName;
	public static String ownSun20SDKftpPassword;
	public static String ownSun20SDKftpPort;
	public static String ownSun20SDKftpPath;
	public static String ownSun20SDKHttpUrl;//针对第五套
	public static String ownSun20SDKHttpUrlForSix;//针对第六套
	/** ***自有客户端 APK加固 end */

	public static String sdkBeforePath;
	public static String sdkAfterPath;

	public static String thirdSun10CHARGEFILEHttpUrl;
	public static String thirdSun15CHARGEFILEHttpUrl;
	public static String thirdSun20CHARGEFILEHttpUrl;

	public static String ownSun10CHARGEFILEHttpUrl;
	public static String ownSun15CHARGEFILEHttpUrl;
	public static String ownSun20CHARGEFILEHttpUrl;
	
	/**文件保存时间*/
	public static String fileSaveDays;
	/**单次任务处理个数上线*/
	public static String cleanCount;
	/*** 客户端应用加固加密 end */

	/**
	 * 终端软件版本管理 软件版本列表导出
	 */
	public static String softwareFilePath;
	/**
	 * 抽奖活动订单导出限制
	 */
	public static int awardOrderExportLimit = 60000;
	/**
	 * 
	 * 
	 * 存储配置项
	 * <tr>
	 * key为属性名
	 */
	public static Map<String, Object> configMap = new HashMap<String, Object>();
	/**
	 * 促销、热门活动维护 基础路径
	 */
	public static String PROMOTION_PLOY_URL;

	/**
	 * 电子书库导出存放地址
	 */
	public static String EBOOK_EXPORT_TASK_FILEPATH;

	/**
	 * 图书导出任务类
	 * 
	 * @param url
	 */
	public static String EBOOK_EXPORT_TASK_CLASS;
	
	/**
	 * BI分省月累计投诉文件ftp服务器配置
	 */
	public static String RDOPROVINCECOMPLAIN_FTP_IP;
	public static String RDOPROVINCECOMPLAIN_FTP_PORT;
	public static String RDOPROVINCECOMPLAIN_FTP_USERNAME;
	public static String RDOPROVINCECOMPLAIN_FTP_PASSWORD;
	/**
	 * BI分省月累计投诉文件ftp服务器路径
	 */
	public static String RDOPROVINCECOMPLAIN_FTP_PATH;
	/**
	 * BI分省月累计投诉文件本地路径
	 */
	public static String RDOPROVINCECOMPLAIN_LOCALPATH;
	/**
	 * 分省关停二级渠道话单本地路径
	 */
	public static String RDOPROVINCEMASKLOG_LOCALPATH;
	/**
	 * 分省关停二级渠道话单本地路径
	 */
	public static String RDOPROVINCEMASKLOG_BUFFERPATH;
	/**
	 * 分省关停二级渠道话单ftp路径
	 */
	public static String RDOPROVINCEMASKLOG_FTP_PATH;
	/**
	 * 深度运营渠道月累计预警的文件本地路径
	 */
	public static String RDOCHANNELALARM_LOCALPATH;
	/**
	 * 图书子系统地址
	 */
	public static String BKS_SYSTEM_PATH;
	/**
	 * 听书子系统地址
	 */
	public static String AUDIOBOOKS_SYSTEM_PATH;
	
	/**
	 * 终端软件补丁包存放根目录
	 */
	public static String SOFTWARE_PATCH_UPLOAD_ROOT_PATH;


	@Value("#{commonServiceConfig['promotionPloyUrl']}")
	public void setPROMOTION_PLOY_URL(String url) {
		ConfigManageUtil.PROMOTION_PLOY_URL = url;
	}

	private static final Logger logger = Logger
			.getLogger(ConfigManageUtil.class);

	/**
	 * 获取配置项
	 * 
	 * @param key
	 *            配置项标识
	 * @return 配置项值
	 */
	public static Object get(String key) {
		return configMap.get(key);
	}

	/**
	 * notify.properties配置文件对应的Properties类
	 */
	public static Properties COMMON_NOTIFY_CONFIG = null;

	@Value("#{commonNotifyConfig}")
	public void setNotifyProperties(Properties properties) {
		try {
			if (ConfigManageUtil.COMMON_NOTIFY_CONFIG == null) {
				ConfigManageUtil.COMMON_NOTIFY_CONFIG = properties;
			} else {
				ConfigManageUtil.COMMON_NOTIFY_CONFIG.putAll(properties);
			}
		} catch (Exception e) {
			logger.error("加载配置文件commonNotifyConfig失败..");
		}
		logger.info("加载配置文件commonNotifyConfig成功");
	}

	public static String getNotifyProperties(String key) {
		return ConfigManageUtil.COMMON_NOTIFY_CONFIG == null ? null
				: ConfigManageUtil.COMMON_NOTIFY_CONFIG.getProperty(key);
	}

	@Value("#{exportConfig['badkeywordUploaderrorlogPath']}")
	public void setBadkeywordUploaderrorlogPath(String path) {
		ConfigManageUtil.badkeywordUploaderrorlogPath = path;
	}

	/**
	 * commonservice.properties配置文件对应的Properties类
	 */
	public static Properties COMMON_SERVICE_CONFIG = null;

	@Value("#{commonServiceConfig}")
	public void setCommonProperties(Properties properties) {
		try {
			if (ConfigManageUtil.COMMON_SERVICE_CONFIG == null) {
				ConfigManageUtil.COMMON_SERVICE_CONFIG = properties;
			} else {
				ConfigManageUtil.COMMON_SERVICE_CONFIG.putAll(properties);
			}
		} catch (Exception e) {
			logger.error("加载配置文件commonServiceConfig失败..");
		}
		logger.info("加载配置文件commonServiceConfig成功");
	}

	public static String getCommonProperties(String key) {
		return COMMON_SERVICE_CONFIG == null ? null : COMMON_SERVICE_CONFIG
				.getProperty(key);
	}

	@Value("#{exportConfig['exportPageMaxSize']}")
	public void setExportPageMaxSize(int exportPageMaxSize) {
		ConfigManageUtil.exportPageMaxSize = exportPageMaxSize;
	}

	@Value("#{exportConfig['grouponPath']}")
	public void setGrouponPath(String grouponPath) {
		ConfigManageUtil.grouponPath = grouponPath;
	}

	@Value("#{commonServiceConfig['loginImage.upload-path']}")
	public void setLoginImageUploadPath(String loginImagePath) {
		ConfigManageUtil.UPLOAD_IMG = loginImagePath;
	}

	@Value("#{commonServiceConfig['rank-blacklist.log']}")
	public void setRankBlackListUploadPath(String rankBlackListUploadPath) {
		ConfigManageUtil.RANK_UPLOAD_RESULT_PATH = rankBlackListUploadPath;
	}

	@Value("#{commonServiceConfig['contentquality.file-path']}")
	public void setContentQualityFilePath(String contentQualityFilePath) {
		ConfigManageUtil.CONTENTQUALITY_FILE_PATH = contentQualityFilePath;
	}

	@Value("#{commonServiceConfig['epubmakerUploadPath']}")
	public void setEPUBMAKERUPLOADPATH(String ePUBMAKERUPLOADPATH) {
		EPUBMAKERUPLOADPATH = ePUBMAKERUPLOADPATH;
	}

	@Value("#{commonServiceConfig['background_task_filepath']}")
	public void setBackgroundTaskFilePath(String path) {
		ConfigManageUtil.BACKGROUND_TASK_FILEPATH = path;
	}

	@Value("#{commonServiceConfig['prize_file_savepath']}")
	public void setPrizeFileSavePath(String path) {
		ConfigManageUtil.PRIZE_FILE_SAVEPATH = path;
	}

	@Value("#{commonServiceConfig['industrybookvideopath']}")
	public void setIndustrybookvideopath(String path) {
		ConfigManageUtil.industrybookvideopath = path;
	}

	@Value("#{commonServiceConfig['default_tmp_file_dir']}")
	public void setDefaultTmpFileDir(String path) {
		ConfigManageUtil.DEFAULT_TMP_FILE_DIR = path;
	}

	@Value("#{commonServiceConfig['usergroup_upload_savepath']}")
	public void setSavepathUsergroup(String path) {
		ConfigManageUtil.USERGROUP_UPLOAD_SAVEPATH = path;
	}

	@Value("#{commonServiceConfig['presentbook_uploadpath']}")
	public void setPresentbookUploadpath(String path) {
		ConfigManageUtil.PRESENTBOOK_UPLOADPATH = path;
	}

	@Value("#{commonServiceConfig['advancecontent_filepath']}")
	public void setAdvancecontentFilePath(String path) {
		ConfigManageUtil.ADVANCECONTENT_FILEPATH = path;
	}

	@Value("#{commonServiceConfig['advancecontent_import_logfilepath']}")
	public void setAdvancecontentImportLogfile(String path) {
		ConfigManageUtil.ADVANCECONTENT_IMPORT_LOGFILEPATH = path;
	}

	@Value("#{commonServiceConfig['usergroup_ftp_localPath']}")
	public void setUsergroup_ftp_localPath(String usergroup_ftp_localPath) {
		ConfigManageUtil.USERGROUP_FTP_LOCALPATH = usergroup_ftp_localPath;
	}

	@Value("#{commonServiceConfig['usergroup_ftp_ip']}")
	public void setUsergroup_ftp_ip(String usergroup_ftp_ip) {
		ConfigManageUtil.USERGROUP_FTP_IP = usergroup_ftp_ip;
	}

	@Value("#{commonServiceConfig['usergroup_ftp_port']}")
	public void setUsergroup_ftp_port(String usergroup_ftp_port) {
		ConfigManageUtil.USERGROUP_FTP_PORT = usergroup_ftp_port;
	}

	@Value("#{commonServiceConfig['usergroup_ftp_userword']}")
	public void setUsergroup_ftp_userword(String usergroup_ftp_userword) {
		ConfigManageUtil.USERGROUP_FTP_USERWORD = usergroup_ftp_userword;
	}

	@Value("#{commonServiceConfig['usergroup_ftp_password']}")
	public void setUsergroup_ftp_password(String usergroup_ftp_password) {
		ConfigManageUtil.USERGROUP_FTP_PASSWORD = usergroup_ftp_password;
	}

	@Value("#{commonServiceConfig['usergroup_ftp_conncectMode']}")
	public void setUsergroup_ftp_conncectMode(String usergroup_ftp_conncectMode) {
		ConfigManageUtil.USERGROUP_FTP_CONNCECTMODE = usergroup_ftp_conncectMode;
	}

	@Value("#{commonServiceConfig['cid_usergroup_bi_ftp_ip']}")
	public void setCidUsergroupBiFtpIp(String path) {
		ConfigManageUtil.CID_USERGROUP_BI_FTP_IP = path;
	}

	@Value("#{commonServiceConfig['cid_usergroup_bi_ftp_port']}")
	public void setCidUsergroupBiFtpPort(String path) {
		ConfigManageUtil.CID_USERGROUP_BI_FTP_PORT = path;
	}

	@Value("#{commonServiceConfig['cid_usergroup_bi_ftp_username']}")
	public void setCidUsergroupBiFtpUsername(String path) {
		ConfigManageUtil.CID_USERGROUP_BI_FTP_USERNAME = path;
	}

	@Value("#{commonServiceConfig['cid_usergroup_bi_ftp_password']}")
	public void setCidUsergroupBiFtpPassword(String path) {
		ConfigManageUtil.CID_USERGROUP_BI_FTP_PASSWORD = path;
	}

	@Value("#{commonServiceConfig['cid_usergroup_local_file_path']}")
	public void setCidUsergroupBiFtpFilepath(String path) {
		ConfigManageUtil.CID_USERGROUP_LOCAL_FILE_PATH = path;
	}

	@Value("#{commonServiceConfig['cid_usergroup_local_file_path_zip']}")
	public void setCidGroupLocalZipFilepath(String path) {
		ConfigManageUtil.CID_USERGROUP_LOCAL_FILE_PATH_ZIP = path;
	}

	@Value("#{commonServiceConfig['recommend_cid_upload_file_path']}")
	public void setRECOMMEND_CID_UPLOAD_FILE_PATH(String path) {
		ConfigManageUtil.RECOMMEND_CID_UPLOAD_FILE_PATH = path;
	}

	@Value("#{commonServiceConfig['usergroup_cgswitch']}")
	public void setUsergroupCgSwitch(String cgswitch) {
		ConfigManageUtil.USERGROUP_CGSWITCH = cgswitch;
	}

	@Value("#{commonServiceConfig['accountSimilayCheckRuleSwitch']}")
	public void setAccountSimilayCheckRuleSwitch(
			String accountSimilayCheckRuleSwitch) {
		ConfigManageUtil.accountSimilayCheckRuleSwitch = accountSimilayCheckRuleSwitch;
	}

	@Value("#{commonServiceConfig['hasDigitCheckRuleSwitch']}")
	public void setHasDigitCheckRuleSwitch(String hasDigitCheckRuleSwitch) {
		ConfigManageUtil.hasDigitCheckRuleSwitch = hasDigitCheckRuleSwitch;
	}

	@Value("#{commonServiceConfig['hasLetterCheckRuleSwitch']}")
	public void setHasLetterCheckRuleSwitch(String hasLetterCheckRuleSwitch) {
		ConfigManageUtil.hasLetterCheckRuleSwitch = hasLetterCheckRuleSwitch;
	}

	@Value("#{commonServiceConfig['hasPunctuationCheckRuleSwitch']}")
	public void setHasPunctuationCheckRuleSwitch(
			String hasPunctuationCheckRuleSwitch) {
		ConfigManageUtil.hasPunctuationCheckRuleSwitch = hasPunctuationCheckRuleSwitch;
	}

	@Value("#{commonServiceConfig['lengthCheckRuleSwitch']}")
	public void setLengthCheckRuleSwitch(String lengthCheckRuleSwitch) {
		ConfigManageUtil.lengthCheckRuleSwitch = lengthCheckRuleSwitch;
	}

	@Value("#{commonServiceConfig['RULES_HPR_PUNCTUATION']}")
	public void setRULES_HPR_PUNCTUATION(String rULES_HPR_PUNCTUATION) {
		RULES_HPR_PUNCTUATION = rULES_HPR_PUNCTUATION;
	}

	@Value("#{commonServiceConfig['minLength']}")
	public void setMinLength(String minLength) {
		ConfigManageUtil.minLength = minLength;
	}

	@Value("#{commonServiceConfig['maxLength']}")
	public void setMaxLength(String maxLength) {
		ConfigManageUtil.maxLength = maxLength;
	}

	@Value("#{commonServiceConfig['authorProceedsLogPath']}")
	public void setAuthorProceedsLogPath(String authorProceedsLogPath) {
		ConfigManageUtil.authorProceedsLogPath = authorProceedsLogPath;
	}

	@Value("#{commonServiceConfig['welcomePageMgrsUploadPath']}")
	public void setWelcomePageMgrsUploadPath(String welcomePageMgrsUploadPath) {
		ConfigManageUtil.welcomePageMgrsUploadPath = welcomePageMgrsUploadPath;
	}

	@Value("#{commonServiceConfig['miguCoin_url']}")
	public void setMiguCoin_url(String miguCoin_url) {
		ConfigManageUtil.miguCoin_url = miguCoin_url;
	}

	@Value("#{commonServiceConfig['migu_uploadPath']}")
	public void setMigu_uploadPath(String migu_uploadPath) {
		ConfigManageUtil.migu_uploadPath = migu_uploadPath;
	}

	@Value("#{commonServiceConfig['migupoint_excelbatch_ready']}")
	public void setMigupoint_excelbatch_ready(String migupoint_excelbatch_ready) {
		ConfigManageUtil.migupoint_excelbatch_ready = migupoint_excelbatch_ready;
	}

	@Value("#{commonServiceConfig['migupoint_excelbatch_done']}")
	public void setMigupoint_excelbatch_done(String migupoint_excelbatch_done) {
		ConfigManageUtil.migupoint_excelbatch_done = migupoint_excelbatch_done;
	}

	@Value("#{commonServiceConfig['sdkProtectMngsUploadPath']}")
	public void setSdkProtectMngsUploadPath(String sdkProtectMngsUploadPath) {
		ConfigManageUtil.sdkProtectMngsUploadPath = sdkProtectMngsUploadPath;
	}

	@Value("#{commonServiceConfig['sdk_receivePath']}")
	public void setSdk_receivePath(String sdk_receivePath) {
		ConfigManageUtil.sdk_receivePath = sdk_receivePath;
	}
	
	@Value("#{commonServiceConfig['sdk_remotePath']}")
	public void setSdk_remotePath(String sdk_remotePath) {
		ConfigManageUtil.sdk_remotePath = sdk_remotePath;
	}
	

	@Value("#{commonServiceConfig['sdk_receiveSavePath']}")
	public void setSdk_receiveSavePath(String sdk_receiveSavePath) {
		ConfigManageUtil.sdk_receiveSavePath = sdk_receiveSavePath;
	}

	@Value("#{commonServiceConfig['control_platform_http']}")
	public void setControl_platform_http(String control_platform_http) {
		ConfigManageUtil.control_platform_http = control_platform_http;
	}

	@Value("#{commonServiceConfig['control_platform_userName']}")
	public void setControl_platform_userName(String control_platform_userName) {
		ConfigManageUtil.control_platform_userName = control_platform_userName;
	}

	@Value("#{commonServiceConfig['control_platform_passWord']}")
	public void setControl_platform_passWord(String control_platform_passWord) {
		ConfigManageUtil.control_platform_passWord = control_platform_passWord;
	}

	@Value("#{commonServiceConfig['control_platform_ip']}")
	public void setControl_platform_ip(String control_platform_ip) {
		ConfigManageUtil.control_platform_ip = control_platform_ip;
	}

	@Value("#{commonServiceConfig['control_platform_port']}")
	public void setControl_platform_port(String control_platform_port) {
		ConfigManageUtil.control_platform_port = control_platform_port;
	}

	@Value("#{commonServiceConfig['control_platform_remotePath']}")
	public void setControl_platform_remotePath(
			String control_platform_remotePath) {
		ConfigManageUtil.control_platform_remotePath = control_platform_remotePath;
	}

	@Value("#{commonServiceConfig['control_platform_shellpath']}")
	public void setControl_platform_shellpath(String control_platform_shellpath) {
		ConfigManageUtil.control_platform_shellpath = control_platform_shellpath;
	}

	@Value("#{commonServiceConfig['send_to_group_url']}")
	public void setSend_to_group_url(String send_to_group_url) {
		ConfigManageUtil.send_to_group_url = send_to_group_url;
	}

	@Value("#{commonServiceConfig['charge_later_filepath']}")
	public void setCharge_later_filepath(String charge_later_filepath) {
		ConfigManageUtil.charge_later_filepath = charge_later_filepath;
	}

	@Value("#{commonServiceConfig['charge_before_filepath']}")
	public void setCharge_before_filepath(String charge_before_filepath) {
		ConfigManageUtil.charge_before_filepath = charge_before_filepath;
	}

	@Value("#{commonServiceConfig['xmluploadpath']}")
	public void setXmluploadpath(String xmluploadpath) {
		ConfigManageUtil.xmluploadpath = xmluploadpath;
	}

	@Value("#{commonServiceConfig['charging_receivePath']}")
	public void setCharging_receivePath(String charging_receivePath) {
		ConfigManageUtil.charging_receivePath = charging_receivePath;
	}

	@Value("#{commonServiceConfig['charging_receiveSavePath']}")
	public void setCharging_receiveSavePath(String charging_receiveSavePath) {
		ConfigManageUtil.charging_receiveSavePath = charging_receiveSavePath;
	}

	@Value("#{commonServiceConfig['authorInfoMngExportPath']}")
	public void setAuthorInfoMngExportPath(String authorInfoMngExportPath) {
		ConfigManageUtil.authorInfoMngExportPath = authorInfoMngExportPath;
	}

	@Value("#{commonServiceConfig['authorProceedsPayUrl']}")
	public void setAuthorProceedsPayUrl(String authorProceedsPayUrl) {
		ConfigManageUtil.authorProceedsPayUrl = authorProceedsPayUrl;
	}

	@Value("#{commonServiceConfig['idCardApacheTomcatPath']}")
	public void setIdCardApacheTomcatPath(String idCardApacheTomcatPath) {
		ConfigManageUtil.idCardApacheTomcatPath = idCardApacheTomcatPath;
	}

	@Value("#{commonServiceConfig['authorApproveSendMailUrl']}")
	public void setAuthorApproveSendMailUrl(String authorApproveSendMailUrl) {
		ConfigManageUtil.authorApproveSendMailUrl = authorApproveSendMailUrl;
	}

	@Value("#{commonServiceConfig['iManager.content.upload-path']}")
	public void setContentUploadPath(String contentUploadPath) {
		ConfigManageUtil.contentUploadPath = contentUploadPath;
	}

	@Value("#{commonServiceConfig['iManager.content.others-recycled-path']}")
	public void setContentRecycledPath(String contentRecycledPath) {
		ConfigManageUtil.contentRecycledPath = contentRecycledPath;
	}

	@Value("#{commonNotifyConfig['notify.NssURL']}")
	public void setNssURL(String url) {
		ConfigManageUtil.NssURL = url;
	}

	@Value("#{commonNotifyConfig['interface.promotionOnOffChange']}")
	public void setPromotionOnOffChange(String urlEnd) {
		// 此处直接放入map
		configMap.put("promotionOnOffChange", urlEnd);
	}

	@Value("#{commonNotifyConfig['interface.authorChange']}")
	public void setAuthorChange(String urlEnd) {
		// configMap.put("authorChange", urlEnd);
		ConfigManageUtil.authorChangePathEnd = urlEnd;
	}

	@Value("#{commonServiceConfig['forumAuthorChangeUrl']}")
	public void setForumAuthorChangeUrl(String forumAuthorChangeUrl) {
		ConfigManageUtil.forumAuthorChangeUrl = forumAuthorChangeUrl;
	}

	@Value("#{commonNotifyConfig['ipGroups.serverGroupIP']}")
	public void setServerGroupIP(String notifyGroupIP) {
		ConfigManageUtil.serverGroupIP = notifyGroupIP;
	}

	@Value("#{commonNotifyConfig['aserverGroupDefault']}")
	public void setAserverGroupDefault(String aserverGroupDefault) {
		// ConfigManageUtil.aserverGroupDefault = aserverGroupDefault;
		configMap.put("aserverGroupDefault", aserverGroupDefault);
	}

	@Value("#{commonNotifyConfig['userChangeToServer']}")
	public void setUserChangeToServer(String userChangeToServer) {
		configMap.put("userChangeToServer", userChangeToServer);
	}

	@Value("#{commonServiceConfig['iManager.notifyDRM.drmAddress.preeIp']}")
	public void setDRMADDRESS_PREEIP(String DRMADDRESS_PREEIP) {
		ConfigManageUtil.DRMADDRESS_PREEIP = DRMADDRESS_PREEIP;
	}

	@Value("#{commonServiceConfig['iManager.notifyDRM.drmAddress.path']}")
	public void setDRMADDRESS_PATH(String DRMADDRESS_PATH) {
		ConfigManageUtil.DRMADDRESS_PATH = DRMADDRESS_PATH;
	}

	@Value("#{commonServiceConfig['PAYMONTH_TASK_BATCH_COUNT']}")
	public void setPAYMONTH_TASK_BATCH_COUNT(String paymonth_task_batch_count) {
		ConfigManageUtil.PAYMONTH_TASK_BATCH_COUNT = paymonth_task_batch_count;
	}

	@Value("#{commonServiceConfig['iManager.softwaretype.name']}")
	public void setSoftwareTypeName(String softwareTypeName) {
		ConfigManageUtil.softwareTypeName = softwareTypeName;
	}

	@Value("#{commonServiceConfig['iManager.softwaretype.value']}")
	public void setSoftwareTypeValue(String softwareTypeValue) {
		ConfigManageUtil.softwareTypeValue = softwareTypeValue;
	}

	@Value("#{commonServiceConfig['apply.switch']}")
	public void setPROMOTION_APPLY_SWITCH(String PROMOTION_APPLY_SWITCH) {
		ConfigManageUtil.PROMOTION_APPLY_SWITCH = PROMOTION_APPLY_SWITCH;
	}

	@Value("#{exportConfig['rdoChannelFilePath']}")
	public void setRdoChannelFilePath(String rdoChannelFilePath) {
		ConfigManageUtil.rdoChannelFilePath = rdoChannelFilePath;
	}

	@Value("#{commonServiceConfig['GRAYQUERY']}")
	public void setGRAYQUERY(String gRAYQUERY) {
		ConfigManageUtil.GRAYQUERY = gRAYQUERY;
	}

	@Value("#{commonServiceConfig['sysPath']}")
	public void setSysPath(String path) {
		ConfigManageUtil.sysPath = path;
	}

	@Value("#{exportConfig['awardOrder.exportPageMaxSize']}")
	public void setAwardOrderExportLimit(int awardOrderExportLimit) {
		ConfigManageUtil.awardOrderExportLimit = awardOrderExportLimit;
	}

	@Value("#{exportConfig['epubmakerUploadPath']}")
	public void setEpubmakerUploadPath(String epubmakerUploadPath) {
		ConfigManageUtil.epubmakerUploadPath = epubmakerUploadPath;
	}

	@Value("#{exportConfig['badInfoManagerFilePath']}")
	public void setBadInfoManagerFilePath(String badInfoManagerFilePath) {
		ConfigManageUtil.badInfoManagerFilePath = badInfoManagerFilePath;
	}

	@Value("#{exportConfig['awardOrderFilePath']}")
	public void setAwardOrderFilePath(String awardOrderFilePath) {
		ConfigManageUtil.awardOrderFilePath = awardOrderFilePath;
	}

	@Value("#{exportConfig['operExportFilePath']}")
	public void setOperExportFilePath(String operExportFilePath) {
		ConfigManageUtil.operExportFilePath = operExportFilePath;
	}

	@Value("#{exportConfig['CMU_Server_IP']}")
	public void setCMU_Server_IP(String CMU_Server_IP) {
		ConfigManageUtil.CMU_Server_IP = CMU_Server_IP;
	}

	@Value("#{exportConfig['authorFilePath']}")
	public void setAuthorFilePath(String authorFilePath) {
		ConfigManageUtil.authorFilePath = authorFilePath;
	}

	@Value("#{exportConfig['mcpRdoChargeInfoDocumentUrl']}")
	public void setMcpRdoChargeInfoDocumentUrl(
			String mcpRdoChargeInfoDocumentUrl) {
		ConfigManageUtil.mcpRdoChargeInfoDocumentUrl = mcpRdoChargeInfoDocumentUrl;
	}

	@Value("#{exportConfig['specialBookListFilePath']}")
	public void setSpecialBookListFilePath(String specialBookListFilePath) {
		ConfigManageUtil.specialBookListFilePath = specialBookListFilePath;
	}

	@Value("#{exportConfig['prmChannelprmFilePath']}")
	public void setPrmChannelprmFilePath(String prmChannelprmFilePath) {
		ConfigManageUtil.prmChannelprmFilePath = prmChannelprmFilePath;
	}

	@Value("#{exportConfig['downloadbookBorrowFilePath']}")
	public void setDownloadbookBorrowFilePath(String downloadbookBorrowFilePath) {
		ConfigManageUtil.downloadbookBorrowFilePath = downloadbookBorrowFilePath;
	}

	@Value("#{exportConfig['pictureTerminaListFilePath']}")
	public void setPictureTerminaListFilePath(String pictureTerminaListFilePath) {
		ConfigManageUtil.pictureTerminaListFilePath = pictureTerminaListFilePath;
	}

	@Value("#{commonServiceConfig['filetmpPath']}")
	public void setFiletmpUploadPath(String filetmpUploadPath) {
		ConfigManageUtil.filetmpUploadPath = filetmpUploadPath;
	}

	@Value("#{commonServiceConfig['mcpAcountFreezeDays']}")
	public void setMcpAcountFreezeDaysSize(int mcpAcountFreezeDaysSize) {
		ConfigManageUtil.mcpAcountFreezeDaysSize = mcpAcountFreezeDaysSize;
	}

	@Value("#{commonServiceConfig['rejectCount']}")
	public void setRejectCountSize(int rejectCountSize) {
		ConfigManageUtil.rejectCountSize = rejectCountSize;
	}

	@Value("#{commonServiceConfig['downloadContent.concurrent.limit']}")
	public void setDownloadContentConcurrenceLimit(
			String downloadContentConcurrenceLimit) {
		ConfigManageUtil.downloadContentConcurrenceLimit = downloadContentConcurrenceLimit;
	}

	@Value("#{notifyConfig['authorInfo_update_delayTime']}")
	public static void setAuthorInfoUpdateDelayTime(String time) {
		ConfigManageUtil.authorInfoUpdateDelayTime = time;
	}

	@Value("#{notifyConfig['CARICATURE_IP']}")
	public static void setCaricatureIp(String ip) {
		ConfigManageUtil.caricatureIp = ip;
	}

	@Value("#{commonServiceConfig['classfy.file-path']}")
	public void setBookclassfyFilePath(String bookclassfyFilePath) {
		ConfigManageUtil.bookclassfyFilePath = bookclassfyFilePath;
	}

	@Value("#{commonServiceConfig['classfy.total-row']}")
	public void setBookclassfyTotalRow(String bookclassfyTotalRow) {
		ConfigManageUtil.bookclassfyTotalRow = bookclassfyTotalRow;
	}

	@Value("#{commonServiceConfig['resumeNotReview.file-path']}")
	public void setResumeNotReviewFilePath(String resumeNotReviewFilePath) {
		ConfigManageUtil.resumeNotReviewFilePath = resumeNotReviewFilePath;
	}

	@Value("#{commonServiceConfig['resumeNotReview.template']}")
	public static void setResumeNotReviewTemplate(String resumeNotReviewTemplate) {
		ConfigManageUtil.resumeNotReviewTemplate = resumeNotReviewTemplate;
	}

	@Value("#{commonServiceConfig['hotBookitem.total-row']}")
	public void setHotBookitemTotalRow(String hotBookitemTotalRow) {
		ConfigManageUtil.hotBookitemTotalRow = hotBookitemTotalRow;
	}

	@Value("#{commonServiceConfig['vbook.file-path']}")
	public void setVbookFilePath(String vbookFilePath) {
		ConfigManageUtil.vbookFilePath = vbookFilePath;
	}

	@Value("#{commonServiceConfig['vbook.sync-delay-time']}")
	public void setVbookSyncDelayTime(String vbookSyncDelayTime) {
		ConfigManageUtil.vbookSyncDelayTime = vbookSyncDelayTime;
	}

	@Value("#{commonServiceConfig['BDC.servtype']}")
	public void setBDCServtype(String BDCServtype) {
		ConfigManageUtil.BDCServtype = BDCServtype;
	}

	@Value("#{commonServiceConfig['packBooksOfAuthorAgainServletUrl']}")
	public void setPackBooksOfAuthorAgainServletUrl(
			String packBooksOfAuthorAgainServletUrl) {
		ConfigManageUtil.packBooksOfAuthorAgainServletUrl = packBooksOfAuthorAgainServletUrl;
	}

	@Value("#{commonServiceConfig['queryMyWorkListUrl']}")
	public void setQueryMyWorkListUrl(String queryMyWorkListUrl) {
		ConfigManageUtil.queryMyWorkListUrl = queryMyWorkListUrl;
	}

	// //////

	@Value("#{commonServiceConfig['KNOWLEDGE_BASE_FILE_SIZE']}")
	public void setKNOWLEDGEBASEFILE_SIZE(String KNOWLEDGE_BASE_FILE_SIZE) {
		ConfigManageUtil.KNOWLEDGE_BASE_FILE_SIZE = KNOWLEDGE_BASE_FILE_SIZE;
	}

	@Value("#{commonServiceConfig['knowledgeFileRootPath']}")
	public void setKnowledgeFileRootPath(String knowledgeFileRootPath) {
		ConfigManageUtil.knowledgeFileRootPath = knowledgeFileRootPath;
	}

	@Value("#{commonServiceConfig['myWorkFilePath']}")
	public void setMyWorkFilePath(String myWorkFilePath) {
		ConfigManageUtil.myWorkFilePath = myWorkFilePath;
	}

	@Value("#{commonServiceConfig['hotBookitemFile']}")
	public void setHotBookitemFile(String hotBookitemFile) {
		ConfigManageUtil.hotBookitemFile = hotBookitemFile;
	}

	@Value("#{commonServiceConfig['hotbook']}")
	public void sethotbook(String hotbook) {
		ConfigManageUtil.hotbook = hotbook;
	}

	@Value("#{commonServiceConfig['UserCommentExport']}")
	public void setUserCommentExport(String userCommentExport) {
		ConfigManageUtil.UserCommentExport = userCommentExport;
	}

	@Value("#{commonServiceConfig['BookLibraryExport']}")
	public void setBookLibraryExport(String bookLibraryExport) {
		ConfigManageUtil.BookLibraryExport = bookLibraryExport;
	}

	@Value("#{commonServiceConfig['UserGroupExport']}")
	public void setUserGroupExport(String userGroupExport) {
		ConfigManageUtil.UserGroupExport = userGroupExport;
	}

	@Value("#{commonServiceConfig['attachmentPath']}")
	public void setAttachmentPath(String attachmentPath) {
		ConfigManageUtil.attachmentPath = attachmentPath;
	}

	@Value("#{exportConfig['softwareFilePath']}")
	public void setSoftwareFilePath(String softwareFilePath) {
		ConfigManageUtil.softwareFilePath = softwareFilePath;
	}

	@Value("#{exportConfig['mobileskinuploadpath']}")
	public void setMobileskinuploadpath(String mobileskinuploadpath) {
		ConfigManageUtil.mobileskinuploadpath = mobileskinuploadpath;
	}

	@Value("#{exportConfig['mobileindustryuploadpath']}")
	public void setMobileindustryuploadpath(String mobileindustryuploadpath) {
		ConfigManageUtil.mobileindustryuploadpath = mobileindustryuploadpath;
	}

	@Value("#{exportConfig['mobileindustrybookuploadpath']}")
	public void setMobileindustrybookuploadpath(
			String mobileindustrybookuploadpath) {
		ConfigManageUtil.mobileindustrybookuploadpath = mobileindustrybookuploadpath;
	}

	@Value("#{commonServiceConfig['rewardDataFilePath']}")
	public void setREWARD_DATA_FILEPATH(String rEWARD_DATA_FILEPATH) {
		REWARD_DATA_FILEPATH = rEWARD_DATA_FILEPATH;
	}

	@Value("#{exportConfig['industrybookloadpath']}")
	public void setIndustrybookloadpath(String industrybookloadpath) {
		ConfigManageUtil.industrybookloadpath = industrybookloadpath;
	}

	@Value("#{exportConfig['seriesbookbanquanpath']}")
	public void setSeriesbookbanquanpath(String seriesbookbanquanpath) {
		ConfigManageUtil.seriesbookbanquanpath = seriesbookbanquanpath;
	}

	@Value("#{commonServiceConfig['enterpriseInfo']}")
	public void setENTERPRISEINFO(String path) {
		ENTERPRISEINFO = path;
	}

	@Value("#{commonServiceConfig['deleteRecommendUnitMemberServletUrl']}")
	public void setDeleteRecommendUnitMemberServletUrl(
			String deleteRecommendUnitMemberServletUrl) {
		ConfigManageUtil.deleteRecommendUnitMemberServletUrl = deleteRecommendUnitMemberServletUrl;
	}

	@Value("#{commonServiceConfig['issnLogoPath']}")
	public void setIssnLogoPath(String issnLogoPath) {
		ConfigManageUtil.issnLogoPath = issnLogoPath;
	}

	@Value("#{commonServiceConfig['issnLogoDataPath']}")
	public void setIssnLogoDataPath(String issnLogoDataPath) {
		ConfigManageUtil.issnLogoDataPath = issnLogoDataPath;
	}

	@Value("#{commonServiceConfig['searchCDLog']}")
	public void setSearchCDLog(String issnLogoDataPath) {
		ConfigManageUtil.searchCDLog = issnLogoDataPath;
	}

	@Value("#{commonServiceConfig['secretkey']}")
	public void setSecretkey(String secretkey) {
		ConfigManageUtil.secretkey = secretkey;
	}

	@Value("#{commonServiceConfig['firstLevle_ftp_localPath']}")
	public void setFirstLevle_ftp_localPath(String firstLevle_ftp_localPath) {
		ConfigManageUtil.firstLevle_ftp_localPath = firstLevle_ftp_localPath;
	}

	@Value("#{commonServiceConfig['firstLevle_ftp_ip']}")
	public void setFirstLevle_ftp_ip(String firstLevle_ftp_ip) {
		ConfigManageUtil.firstLevle_ftp_ip = firstLevle_ftp_ip;
	}

	@Value("#{commonServiceConfig['firstLevle_ftp_port']}")
	public void setFirstLevle_ftp_port(String firstLevle_ftp_port) {
		ConfigManageUtil.firstLevle_ftp_port = firstLevle_ftp_port;
	}

	@Value("#{commonServiceConfig['firstLevle_ftp_userword']}")
	public void setFirstLevle_ftp_userword(String firstLevle_ftp_userword) {
		ConfigManageUtil.firstLevle_ftp_userword = firstLevle_ftp_userword;
	}

	@Value("#{commonServiceConfig['firstLevle_ftp_password']}")
	public void setFirstLevle_ftp_password(String firstLevle_ftp_password) {
		ConfigManageUtil.firstLevle_ftp_password = firstLevle_ftp_password;
	}

	@Value("#{commonServiceConfig['firstLevle_conncectMode']}")
	public void setFirstLevle_conncectMode(String firstLevle_conncectMode) {
		ConfigManageUtil.firstLevle_conncectMode = firstLevle_conncectMode;
	}

	@Value("#{commonServiceConfig['firstLevle_uploadbin']}")
	public void setFirstLevle_uploadbin(String firstLevle_uploadbin) {
		ConfigManageUtil.firstLevle_uploadbin = firstLevle_uploadbin;
	}

	@Value("#{commonServiceConfig['secretkey_of_comic']}")
	public void setSecretkey_of_comic(String secretkey_of_comic) {
		ConfigManageUtil.secretkey_of_comic = secretkey_of_comic;
	}

	@Value("#{commonServiceConfig['secretkey_of_magazine']}")
	public void setSecretkey_of_magazine(String secretkey_of_magazine) {
		ConfigManageUtil.secretkey_of_magazine = secretkey_of_magazine;
	}

	@Value("#{commonServiceConfig['thirdSun10SDKftpIp']}")
	public void setThirdSun10SDKftpIp(String thirdSun10SDKftpIp) {
		ConfigManageUtil.thirdSun10SDKftpIp = thirdSun10SDKftpIp;
	}

	@Value("#{commonServiceConfig['thirdSun10SDKftpUserName']}")
	public void setThirdSun10SDKftpUserName(String thirdSun10SDKftpUserName) {
		ConfigManageUtil.thirdSun10SDKftpUserName = thirdSun10SDKftpUserName;
	}

	@Value("#{commonServiceConfig['thirdSun10SDKftpPassword']}")
	public void setThirdSun10SDKftpPassword(String thirdSun10SDKftpPassword) {
		ConfigManageUtil.thirdSun10SDKftpPassword = thirdSun10SDKftpPassword;
	}

	@Value("#{commonServiceConfig['thirdSun10SDKftpPort']}")
	public void setThirdSun10SDKftpPort(String thirdSun10SDKftpPort) {
		ConfigManageUtil.thirdSun10SDKftpPort = thirdSun10SDKftpPort;
	}

	@Value("#{commonServiceConfig['thirdSun10SDKftpPath']}")
	public void setThirdSun10SDKftpPath(String thirdSun10SDKftpPath) {
		ConfigManageUtil.thirdSun10SDKftpPath = thirdSun10SDKftpPath;
	}

	@Value("#{commonServiceConfig['thirdSun10SDKHttpUrl']}")
	public void setThirdSun10SDKHttpUrl(String thirdSun10SDKHttpUrl) {
		ConfigManageUtil.thirdSun10SDKHttpUrl = thirdSun10SDKHttpUrl;
	}

	@Value("#{commonServiceConfig['thirdSun15SDKftpIp']}")
	public void setThirdSun15SDKftpIp(String thirdSun15SDKftpIp) {
		ConfigManageUtil.thirdSun15SDKftpIp = thirdSun15SDKftpIp;
	}

	@Value("#{commonServiceConfig['thirdSun15SDKftpUserName']}")
	public void setThirdSun15SDKftpUserName(String thirdSun15SDKftpUserName) {
		ConfigManageUtil.thirdSun15SDKftpUserName = thirdSun15SDKftpUserName;
	}

	@Value("#{commonServiceConfig['thirdSun15SDKftpPassword']}")
	public void setThirdSun15SDKftpPassword(String thirdSun15SDKftpPassword) {
		ConfigManageUtil.thirdSun15SDKftpPassword = thirdSun15SDKftpPassword;
	}

	@Value("#{commonServiceConfig['thirdSun15SDKftpPort']}")
	public void setThirdSun15SDKftpPort(String thirdSun15SDKftpPort) {
		ConfigManageUtil.thirdSun15SDKftpPort = thirdSun15SDKftpPort;
	}

	@Value("#{commonServiceConfig['thirdSun15SDKftpPath']}")
	public void setThirdSun15SDKftpPath(String thirdSun15SDKftpPath) {
		ConfigManageUtil.thirdSun15SDKftpPath = thirdSun15SDKftpPath;
	}

	@Value("#{commonServiceConfig['thirdSun15SDKHttpUrl']}")
	public void setThirdSun15SDKHttpUrl(String thirdSun15SDKHttpUrl) {
		ConfigManageUtil.thirdSun15SDKHttpUrl = thirdSun15SDKHttpUrl;
	}

	@Value("#{commonServiceConfig['thirdSun20SDKftpIp']}")
	public void setThirdSun20SDKftpIp(String thirdSun20SDKftpIp) {
		ConfigManageUtil.thirdSun20SDKftpIp = thirdSun20SDKftpIp;
	}

	@Value("#{commonServiceConfig['thirdSun20SDKftpUserName']}")
	public void setThirdSun20SDKftpUserName(String thirdSun20SDKftpUserName) {
		ConfigManageUtil.thirdSun20SDKftpUserName = thirdSun20SDKftpUserName;
	}

	@Value("#{commonServiceConfig['thirdSun20SDKftpPassword']}")
	public void setThirdSun20SDKftpPassword(String thirdSun20SDKftpPassword) {
		ConfigManageUtil.thirdSun20SDKftpPassword = thirdSun20SDKftpPassword;
	}

	@Value("#{commonServiceConfig['thirdSun20SDKftpPort']}")
	public void setThirdSun20SDKftpPort(String thirdSun20SDKftpPort) {
		ConfigManageUtil.thirdSun20SDKftpPort = thirdSun20SDKftpPort;
	}

	@Value("#{commonServiceConfig['thirdSun20SDKftpPath']}")
	public void setThirdSun20SDKftpPath(String thirdSun20SDKftpPath) {
		ConfigManageUtil.thirdSun20SDKftpPath = thirdSun20SDKftpPath;
	}

	@Value("#{commonServiceConfig['thirdSun20SDKHttpUrl']}")
	public void setThirdSun20SDKHttpUrlFor(String thirdSun20SDKHttpUrl) {
		ConfigManageUtil.thirdSun20SDKHttpUrl = thirdSun20SDKHttpUrl;
	}
	
	@Value("#{commonServiceConfig['thirdSun20SDKHttpUrlForSix']}")
	public void setThirdSun20SDKHttpUrlForSix(String thirdSun20SDKHttpUrlForSix) {
		ConfigManageUtil.thirdSun20SDKHttpUrlForSix = thirdSun20SDKHttpUrlForSix;
	}

	@Value("#{commonServiceConfig['ownSun10SDKftpIp']}")
	public void setOwnSun10SDKftpIp(String ownSun10SDKftpIp) {
		ConfigManageUtil.ownSun10SDKftpIp = ownSun10SDKftpIp;
	}

	@Value("#{commonServiceConfig['ownSun10SDKftpUserName']}")
	public void setOwnSun10SDKftpUserName(String ownSun10SDKftpUserName) {
		ConfigManageUtil.ownSun10SDKftpUserName = ownSun10SDKftpUserName;
	}

	@Value("#{commonServiceConfig['ownSun10SDKftpPassword']}")
	public void setOwnSun10SDKftpPassword(String ownSun10SDKftpPassword) {
		ConfigManageUtil.ownSun10SDKftpPassword = ownSun10SDKftpPassword;
	}

	@Value("#{commonServiceConfig['ownSun10SDKftpPort']}")
	public void setOwnSun10SDKftpPort(String ownSun10SDKftpPort) {
		ConfigManageUtil.ownSun10SDKftpPort = ownSun10SDKftpPort;
	}

	@Value("#{commonServiceConfig['ownSun10SDKftpPath']}")
	public void setOwnSun10SDKftpPath(String ownSun10SDKftpPath) {
		ConfigManageUtil.ownSun10SDKftpPath = ownSun10SDKftpPath;
	}

	@Value("#{commonServiceConfig['ownSun10SDKHttpUrl']}")
	public void setOwnSun10SDKHttpUrl(String ownSun10SDKHttpUrl) {
		ConfigManageUtil.ownSun10SDKHttpUrl = ownSun10SDKHttpUrl;
	}

	@Value("#{commonServiceConfig['ownSun15SDKftpIp']}")
	public void setOwnSun15SDKftpIp(String ownSun15SDKftpIp) {
		ConfigManageUtil.ownSun15SDKftpIp = ownSun15SDKftpIp;
	}

	@Value("#{commonServiceConfig['ownSun15SDKftpUserName']}")
	public void setOwnSun15SDKftpUserName(String ownSun15SDKftpUserName) {
		ConfigManageUtil.ownSun15SDKftpUserName = ownSun15SDKftpUserName;
	}

	@Value("#{commonServiceConfig['ownSun15SDKftpPassword']}")
	public void setOwnSun15SDKftpPassword(String ownSun15SDKftpPassword) {
		ConfigManageUtil.ownSun15SDKftpPassword = ownSun15SDKftpPassword;
	}

	@Value("#{commonServiceConfig['ownSun15SDKftpPort']}")
	public void setOwnSun15SDKftpPort(String ownSun15SDKftpPort) {
		ConfigManageUtil.ownSun15SDKftpPort = ownSun15SDKftpPort;
	}

	@Value("#{commonServiceConfig['ownSun15SDKftpPath']}")
	public void setOwnSun15SDKftpPath(String ownSun15SDKftpPath) {
		ConfigManageUtil.ownSun15SDKftpPath = ownSun15SDKftpPath;
	}

	@Value("#{commonServiceConfig['ownSun15SDKHttpUrl']}")
	public void setOwnSun15SDKHttpUrl(String ownSun15SDKHttpUrl) {
		ConfigManageUtil.ownSun15SDKHttpUrl = ownSun15SDKHttpUrl;
	}

	@Value("#{commonServiceConfig['ownSun20SDKftpIp']}")
	public void setOwnSun20SDKftpIp(String ownSun20SDKftpIp) {
		ConfigManageUtil.ownSun20SDKftpIp = ownSun20SDKftpIp;
	}

	@Value("#{commonServiceConfig['ownSun20SDKHttpUrlForSix']}")
	public void setOwnSun20SDKftpIpForSix(String ownSun20SDKHttpUrlForSix) {
		ConfigManageUtil.ownSun20SDKHttpUrlForSix = ownSun20SDKHttpUrlForSix;
	}
	@Value("#{commonServiceConfig['ownSun20SDKftpUserName']}")
	public void setOwnSun20SDKftpUserName(String ownSun20SDKftpUserName) {
		ConfigManageUtil.ownSun20SDKftpUserName = ownSun20SDKftpUserName;
	}

	@Value("#{commonServiceConfig['ownSun20SDKftpPassword']}")
	public void setOwnSun20SDKftpPassword(String ownSun20SDKftpPassword) {
		ConfigManageUtil.ownSun20SDKftpPassword = ownSun20SDKftpPassword;
	}

	@Value("#{commonServiceConfig['ownSun20SDKftpPort']}")
	public void setOwnSun20SDKftpPort(String ownSun20SDKftpPort) {
		ConfigManageUtil.ownSun20SDKftpPort = ownSun20SDKftpPort;
	}

	@Value("#{commonServiceConfig['ownSun20SDKftpPath']}")
	public void setOwnSun20SDKftpPath(String ownSun20SDKftpPath) {
		ConfigManageUtil.ownSun20SDKftpPath = ownSun20SDKftpPath;
	}

	@Value("#{commonServiceConfig['ownSun20SDKHttpUrl']}")
	public void setOwnSun20SDKHttpUrl(String ownSun20SDKHttpUrl) {
		ConfigManageUtil.ownSun20SDKHttpUrl = ownSun20SDKHttpUrl;
	}

	@Value("#{commonServiceConfig['sdkBeforePath']}")
	public void setSdkBeforePath(String sdkBeforePath) {
		ConfigManageUtil.sdkBeforePath = sdkBeforePath;
	}

	@Value("#{commonServiceConfig['sdkAfterPath']}")
	public void setSdkAfterPath(String sdkAfterPath) {
		ConfigManageUtil.sdkAfterPath = sdkAfterPath;
	}

	@Value("#{commonServiceConfig['thirdSun10CHARGEFILEHttpUrl']}")
	public void setThirdSun10CHARGEFILEHttpUrl(
			String thirdSun10CHARGEFILEHttpUrl) {
		ConfigManageUtil.thirdSun10CHARGEFILEHttpUrl = thirdSun10CHARGEFILEHttpUrl;
	}

	@Value("#{commonServiceConfig['thirdSun15CHARGEFILEHttpUrl']}")
	public void setThirdSun15CHARGEFILEHttpUrl(
			String thirdSun15CHARGEFILEHttpUrl) {
		ConfigManageUtil.thirdSun15CHARGEFILEHttpUrl = thirdSun15CHARGEFILEHttpUrl;
	}

	@Value("#{commonServiceConfig['thirdSun20CHARGEFILEHttpUrl']}")
	public void setThirdSun20CHARGEFILEHttpUrl(
			String thirdSun20CHARGEFILEHttpUrl) {
		ConfigManageUtil.thirdSun20CHARGEFILEHttpUrl = thirdSun20CHARGEFILEHttpUrl;
	}

	@Value("#{commonServiceConfig['ownSun10CHARGEFILEHttpUrl']}")
	public void setOwnSun10CHARGEFILEHttpUrl(String ownSun10CHARGEFILEHttpUrl) {
		ConfigManageUtil.ownSun10CHARGEFILEHttpUrl = ownSun10CHARGEFILEHttpUrl;
	}

	@Value("#{commonServiceConfig['ownSun15CHARGEFILEHttpUrl']}")
	public void setOwnSun15CHARGEFILEHttpUrl(String ownSun15CHARGEFILEHttpUrl) {
		ConfigManageUtil.ownSun15CHARGEFILEHttpUrl = ownSun15CHARGEFILEHttpUrl;
	}

	@Value("#{commonServiceConfig['ownSun20CHARGEFILEHttpUrl']}")
	public void setOwnSun20CHARGEFILEHttpUrl(String ownSun20CHARGEFILEHttpUrl) {
		ConfigManageUtil.ownSun20CHARGEFILEHttpUrl = ownSun20CHARGEFILEHttpUrl;
	}
	
	@Value("#{commonServiceConfig['fileSaveDays']}")
	public void setFileSaveDays(String fileSaveDays){
		ConfigManageUtil.fileSaveDays = fileSaveDays;
	}
	
	@Value("#{commonServiceConfig['cleanCount']}")
	public void setCleanCount(String cleanCount){
		ConfigManageUtil.cleanCount = cleanCount;
	}

	@Value("#{commonServiceConfig['copyrightThawUrl']}")
	public void setCOPYRIGHT_THAW_URL(String cOPYRIGHT_THAW_URL) {
		COPYRIGHT_THAW_URL = cOPYRIGHT_THAW_URL;
	}

	@Value("#{commonServiceConfig['copyrgiht.file-path']}")
	public void setCOPYRIGHT_FILE_PATH(String cCOPYRIGHT_FILE_PATH) {
		COPYRIGHT_FILE_PATH = cCOPYRIGHT_FILE_PATH;
	}

	@Value("#{commonServiceConfig['copyrightAutoShelvesSwitch']}")
	public void setCOPYRIGHT_AUTO_SHELVES_SWITCH(
			String cCOPYRIGHT_AUTO_SHELVES_SWITCH) {
		COPYRIGHT_AUTO_SHELVES_SWITCH = cCOPYRIGHT_AUTO_SHELVES_SWITCH;
	}

	@Value("#{commonServiceConfig['copyright-file-size']}")
	public void setCOPYRIGHT_FILE_SIZE(String cOPYRIGHT_FILE_SIZE) {
		COPYRIGHT_FILE_SIZE = cOPYRIGHT_FILE_SIZE;
	}

	@Value("#{commonServiceConfig['copyrightFreezeAndBookOffShelfUrl']}")
	public void setCOPYRIGHT_FREEZE_AND_BOOK_OFF_SHELF_URL(
			String cOPYRIGHT_FREEZE_AND_BOOK_OFF_SHELF_URL) {
		COPYRIGHT_FREEZE_AND_BOOK_OFF_SHELF_URL = cOPYRIGHT_FREEZE_AND_BOOK_OFF_SHELF_URL;
	}

	@Value("#{commonServiceConfig['ebookExportFilepath']}")
	public void setEBOOK_EXPORT_TASK_FILEPATH(String eBOOK_EXPORT_TASK_FILEPATH) {
		EBOOK_EXPORT_TASK_FILEPATH = eBOOK_EXPORT_TASK_FILEPATH;
	}

	@Value("#{commonServiceConfig['ebookExportClass']}")
	public void setEBOOK_EXPORT_TASK_CLASS(String eBOOK_EXPORT_TASK_CLASS) {
		EBOOK_EXPORT_TASK_CLASS = eBOOK_EXPORT_TASK_CLASS;
	}

	@Value("#{commonServiceConfig['rdoProvinceComplain_ftp_ip']}")
	public void setRDOPROVINCECOMPLAIN_FTP_IP(String rDOPROVINCECOMPLAIN_FTP_IP) {
		RDOPROVINCECOMPLAIN_FTP_IP = rDOPROVINCECOMPLAIN_FTP_IP;
	}

	@Value("#{commonServiceConfig['rdoProvinceComplain_ftp_port']}")
	public void setRDOPROVINCECOMPLAIN_FTP_PORT(String rDOPROVINCECOMPLAIN_FTP_PORT) {
		RDOPROVINCECOMPLAIN_FTP_PORT = rDOPROVINCECOMPLAIN_FTP_PORT;
	}

	@Value("#{commonServiceConfig['rdoProvinceComplain_ftp_username']}")
	public void setRDOPROVINCECOMPLAIN_FTP_USERNAME(String rDOPROVINCECOMPLAIN_FTP_USERNAME) {
		RDOPROVINCECOMPLAIN_FTP_USERNAME = rDOPROVINCECOMPLAIN_FTP_USERNAME;
	}

	@Value("#{commonServiceConfig['rdoProvinceComplain_ftp_password']}")
	public void setRDOPROVINCECOMPLAIN_FTP_PASSWORD(String rDOPROVINCECOMPLAIN_FTP_PASSWORD) {
		RDOPROVINCECOMPLAIN_FTP_PASSWORD = rDOPROVINCECOMPLAIN_FTP_PASSWORD;
	}
	
	
	@Value("#{commonServiceConfig['rdoProvinceComplain_ftp_path']}")
	public void setRDOPROVINCECOMPLAIN_FTP_PATH(String rDOPROVINCECOMPLAIN_FTP_PATH) {
		RDOPROVINCECOMPLAIN_FTP_PATH = rDOPROVINCECOMPLAIN_FTP_PATH;
	}
	
	@Value("#{commonServiceConfig['rdoProvinceComplain_localpath']}")
	public void setRDOPROVINCECOMPLAIN_LOCALPATH(String rDOPROVINCECOMPLAIN_LOCALPATH) {
		RDOPROVINCECOMPLAIN_LOCALPATH = rDOPROVINCECOMPLAIN_LOCALPATH;
	}

	@Value("#{commonServiceConfig['rdoProvinceMaskLog_localpath']}")
	public void setRDOPROVINCEMASKLOG_LOCALPATH(String rDOPROVINCEMASKLOG_LOCALPATH) {
		RDOPROVINCEMASKLOG_LOCALPATH = rDOPROVINCEMASKLOG_LOCALPATH;
	}
	
	@Value("#{commonServiceConfig['rdoProvinceMaskLog_bufferpath']}")
	public void setRDOPROVINCEMASKLOG_BUFFERPATH(String rDOPROVINCEMASKLOG_BUFFERPATH) {
		RDOPROVINCEMASKLOG_BUFFERPATH = rDOPROVINCEMASKLOG_BUFFERPATH;
	}
	
	@Value("#{commonServiceConfig['rdoProvinceMaskLog_ftp_path']}")
	public void setRDOPROVINCEMASKLOG_FTP_PATH(String rDOPROVINCEMASKLOG_FTP_PATH) {
		RDOPROVINCEMASKLOG_FTP_PATH = rDOPROVINCEMASKLOG_FTP_PATH;
	}
	
	@Value("#{commonServiceConfig['rdoChannelAlarm_localpath']}")
	public void setRDOCHANNELALARM_LOCALPATH(String rDOCHANNELALARM_LOCALPATH) {
		RDOCHANNELALARM_LOCALPATH = rDOCHANNELALARM_LOCALPATH;
	}
	@Value("#{commonServiceConfig['BKS_SYSTEM_PATH']}")
	public void setBKS_SYSTEM_PATH(String rBKS_SYSTEM_PATH) {
		BKS_SYSTEM_PATH = rBKS_SYSTEM_PATH;
	}
	@Value("#{commonServiceConfig['AUDIOBOOKS_SYSTEM_PATH']}")
	public void setAUDIOBOOKS_SYSTEM_PATH(String rAUDIOBOOKS_SYSTEM_PATH) {
		AUDIOBOOKS_SYSTEM_PATH = rAUDIOBOOKS_SYSTEM_PATH;
	}

	@Value("#{commonServiceConfig['wapsdk.get.netid.url.ctype']}")
	public void setWapsdkGetNetidUrlCtype(String wapsdkGetNetidUrlCtype) {
		ConfigManageUtil.wapsdkGetNetidUrlCtype = wapsdkGetNetidUrlCtype;
	}

	@Value("#{commonServiceConfig['zfb.live.num.mng.cover.save.dir']}")
	public void setZfbLiveNumMngCoverSaveDir(String zfbLiveNumMngCoverSaveDir) {
		ConfigManageUtil.zfbLiveNumMngCoverSaveDir = zfbLiveNumMngCoverSaveDir;
	}

	@Value("#{commonServiceConfig['zfb.live.num.mng.catalogId']}")
	public void setZfbLiveNumMngCatalogId(String zfbLiveNumMngCatalogId) {
		ConfigManageUtil.zfbLiveNumMngCatalogId = zfbLiveNumMngCatalogId;
	}

	@Value("#{commonServiceConfig['zfb.live.num.mng.contactTel']}")
	public void setZfbLiveNumMngContactTel(String zfbLiveNumMngContactTel) {
		ConfigManageUtil.zfbLiveNumMngContactTel = zfbLiveNumMngContactTel;
	}

	@Value("#{commonServiceConfig['zfb.live.num.mng.contactEmail']}")
	public void setZfbLiveNumMngContactEmail(String zfbLiveNumMngContactEmail) {
		ConfigManageUtil.zfbLiveNumMngContactEmail = zfbLiveNumMngContactEmail;
	}

	@Value("#{commonServiceConfig['wapsdk.get.netid.url']}")
	public void setWapsdkGetNetidUrl(String wapsdkGetNetidUrl) {
		ConfigManageUtil.wapsdkGetNetidUrl = wapsdkGetNetidUrl;
	}
	
	@Value("#{commonServiceConfig['SyncBIChannelInfoServlet.NotifyUrl']}")
	public void setSyncBIChannelInfoServletNotifyUrl(
			String syncBIChannelInfoServletNotifyUrl) {
		SyncBIChannelInfoServletNotifyUrl = syncBIChannelInfoServletNotifyUrl;
	}
	
	@Value("#{commonServiceConfig['softwarePatch.uploadPath']}")
	public void setSOFTWARE_PATCH_UPLOAD_ROOT_PATH(String sOFTWARE_PATCH_UPLOAD_ROOT_PATH) {
		ConfigManageUtil.SOFTWARE_PATCH_UPLOAD_ROOT_PATH = sOFTWARE_PATCH_UPLOAD_ROOT_PATH;
	}

	@Value("#{commonServiceConfig['wapsdk.get.netid.register.url']}")
	public void setWapsdkGetNetidRegisterUrl(String wapsdkGetNetidRegisterUrl) {
		ConfigManageUtil.wapsdkGetNetidRegisterUrl = wapsdkGetNetidRegisterUrl;
	}
	
}
