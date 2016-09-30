package spring.propertiesToBean;


public class Application
{

    /**
     * VMS 上传路径配置
     */
    private String vmsUploadPath;

    /**
     * VMS 上传路径home目录配置
     */
    private String vmsUploadHomePath;

    /**
     */
    private String contUploadUrl;

    /**
     * 各业务平台上传的基础路径(过时)
     */
    @Deprecated
    private String uploadPath;

    /**
     * 视频能力部件aes加密解密密钥
     */
    private String aesEncryptKey;

    /**
     * 视频能力部件des加密解密密钥
     */
    private String desEncryptKey;

    /**
     * #0:采用FTP进行下载方式 1：采用磁盘挂载方式
     */
    private String ftpSwitch;

    /**
     * 内容对账信息文件下载的基础路径
     */
    private String contentReconciliationPath;

    /**
     * 视频文件名称匹配
     */
    private String videoCodeNamePattern;

    /**
     * 视频文件名称前缀
     */
    private String videoCodeNamePreFix;

    /**
     * 视频内容名称匹配
     */
    private String videoContentNamePattern;

    /**
     * 视频内容文件名称前缀
     */
    private String videoContentNamePreFix;

    /**
     * 视频信息存放路径
     */
    private String videoInfoFilePath;

    /**
     * 分发job运行频率 单位：分钟
     */
    private int queryContentDataFreq;

    /**
     * 会话保持频率 单位：分钟
     */
    private int sessionHoldFreq;

    /**
     * 查询视频上传信息页面每页数量配置
     */
    private int uploadPageSize;

    /**
     * 图片保存根目录地址
     */
    private String imageRootPath;

    /**
     * 图片下载公网地址
     */
    private String imageDownloadUrl;

    /**
     * 业务方分发白名单 add by jwx194590
     */
    private String businessDistributeWhitelist;

    /**
     * 保存时间的文件路径
     */
    private String lastTimeSavePath;

    /**
     * 内容（视频/音频）的播放和下载地址前缀表达式
     */
    private String contentUrlPrefix;

    /**
     * 内容查询接口查询时间范围便宜量，单位：分钟
     */
    private String queryContentOffset;

    /**
     * 自动播控，查询待播控节目的时间范围，单位：天
     */
    private String autoPlayControlFreq;

    /**
     * 自动播控接口地址
     */

    private String autoPlayControlUrl;

    /**
     * 播控时BCID是否赋值
     */
    private String autoPlayControlBcId;

    /**
     * 重新发布超时时长（分）
     */
    private String releaseTimeMinute;

    public String getReleaseTimeMinute()
    {
        return releaseTimeMinute;
    }

    public void setReleaseTimeMinute(String releaseTimeMinute)
    {
        this.releaseTimeMinute = releaseTimeMinute;
    }

    public String getAutoPlayControlBcId()
    {
        return autoPlayControlBcId;
    }

    public void setAutoPlayControlBcId(String autoPlayControlBcId)
    {
        this.autoPlayControlBcId = autoPlayControlBcId;
    }

    public String getAutoPlayControlUrl()
    {
        return autoPlayControlUrl;
    }

    public void setAutoPlayControlUrl(String autoPlayControlUrl)
    {
        this.autoPlayControlUrl = autoPlayControlUrl;
    }

    public String getContUploadUrl()
    {
        return contUploadUrl;
    }

    public void setContUploadUrl(String contUploadUrl)
    {
        this.contUploadUrl = contUploadUrl;
    }

    @Deprecated
    public String getUploadPath()
    {
        return uploadPath;
    }

    @Deprecated
    public void setUploadPath(String uploadPath)
    {
        this.uploadPath = uploadPath;
    }

    public String getContentReconciliationPath()
    {
        return contentReconciliationPath;
    }

    public void setContentReconciliationPath(String contentReconciliationPath)
    {
        this.contentReconciliationPath = contentReconciliationPath;
    }

    public String getVideoCodeNamePattern()
    {
        return videoCodeNamePattern;
    }

    public void setVideoCodeNamePattern(String videoCodeNamePattern)
    {
        this.videoCodeNamePattern = videoCodeNamePattern;
    }

    public String getVideoContentNamePattern()
    {
        return videoContentNamePattern;
    }

    public void setVideoContentNamePattern(String videoContentNamePattern)
    {
        this.videoContentNamePattern = videoContentNamePattern;
    }

    public String getVideoCodeNamePreFix()
    {
        return videoCodeNamePreFix;
    }

    public void setVideoCodeNamePreFix(String videoCodeNamePreFix)
    {
        this.videoCodeNamePreFix = videoCodeNamePreFix;
    }

    public String getVideoContentNamePreFix()
    {
        return videoContentNamePreFix;
    }

    public void setVideoContentNamePreFix(String videoContentNamePreFix)
    {
        this.videoContentNamePreFix = videoContentNamePreFix;
    }

    public String getAesEncryptKey()
    {
        return aesEncryptKey;
    }

    public void setAesEncryptKey(String aesEncryptKey)
    {
        this.aesEncryptKey = aesEncryptKey;
    }

    public String getFtpSwitch()
    {
        return ftpSwitch;
    }

    public void setFtpSwitch(String ftpSwitch)
    {
        this.ftpSwitch = ftpSwitch;
    }

    public String getVideoInfoFilePath()
    {
        return videoInfoFilePath;
    }

    public void setVideoInfoFilePath(String videoInfoFilePath)
    {
        this.videoInfoFilePath = videoInfoFilePath;
    }

    public String getDesEncryptKey()
    {
        return desEncryptKey;
    }

    public void setDesEncryptKey(String desEncryptKey)
    {
        this.desEncryptKey = desEncryptKey;
    }

    public int getQueryContentDataFreq()
    {
        return queryContentDataFreq;
    }

    public void setQueryContentDataFreq(int queryContentDataFreq)
    {
        this.queryContentDataFreq = queryContentDataFreq;
    }

    public String getVmsUploadPath()
    {
        return vmsUploadPath;
    }

    public void setVmsUploadPath(String vmsUploadPath)
    {
        this.vmsUploadPath = vmsUploadPath;
    }

    public int getSessionHoldFreq()
    {
        return sessionHoldFreq;
    }

    public void setSessionHoldFreq(int sessionHoldFreq)
    {
        this.sessionHoldFreq = sessionHoldFreq;
    }

    public int getUploadPageSize()
    {
        return uploadPageSize;
    }

    public void setUploadPageSize(int uploadPageSize)
    {
        this.uploadPageSize = uploadPageSize;
    }

    public String getVmsUploadHomePath()
    {
        return vmsUploadHomePath;
    }

    public void setVmsUploadHomePath(String vmsUploadHomePath)
    {
        this.vmsUploadHomePath = vmsUploadHomePath;
    }

    public String getBusinessDistributeWhitelist()
    {
        return businessDistributeWhitelist;
    }

    public void setBusinessDistributeWhitelist(
            String businessDistributeWhitelist)
    {
        this.businessDistributeWhitelist = businessDistributeWhitelist;
    }

    public String getLastTimeSavePath()
    {
        return lastTimeSavePath;
    }

    public void setLastTimeSavePath(String lastTimeSavePath)
    {
        this.lastTimeSavePath = lastTimeSavePath;
    }

    /**
     * 获取VMS上传路径配置
     */
    public String getVmsUploadFilePath()
    {
        return this.vmsUploadHomePath + this.vmsUploadPath;
    }

    public String getImageRootPath()
    {
        return imageRootPath;
    }

    public void setImageRootPath(String imageRootPath)
    {
        this.imageRootPath = imageRootPath;
    }

    public String getImageDownloadUrl()
    {
        return imageDownloadUrl;
    }

    public void setImageDownloadUrl(String imageDownloadUrl)
    {
        this.imageDownloadUrl = imageDownloadUrl;
    }

    public String getContentUrlPrefix()
    {
        return contentUrlPrefix;
    }

    public void setContentUrlPrefix(String contentUrlPrefix)
    {
        this.contentUrlPrefix = contentUrlPrefix;
    }

    public String getQueryContentOffset()
    {
        return queryContentOffset;
    }

    public void setQueryContentOffset(String queryContentOffset)
    {
        this.queryContentOffset = queryContentOffset;
    }

    public String getAutoPlayControlFreq()
    {
        return autoPlayControlFreq;
    }

    public void setAutoPlayControlFreq(String autoPlayControlFreq)
    {
        this.autoPlayControlFreq = autoPlayControlFreq;
    }

}