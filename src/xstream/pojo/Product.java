package xstream.pojo;

import java.util.List;

public class Product
{
    /**
     * 节目ID
     */
    private String prdContId;

    /**
     * 产品包ID
     */
    private String prdInfoId;

    /**
     * 产品包名称
     */
    private String prdInfoName;

    /**
     * 内容ID
     */
    private String contentId;

    /**
     * 媒资ID
     */
    private String assetId;

    /**
     * 内容名称
     */
    private String contentName;

    /**
     * 内容简称
     */
    private String shortName;

    /**
     * 内容备注
     */
    private String detail;

    /**
     * 播放时长
     */
    private String cduration;

    /**
     * 播控方标识
     */
    private String bcId;

    /**
     * 合作伙伴编号
     */
    private String cpId;

    /**
     * 内容所属CP编号
     */
    private String ncpId;

    /**
     * 版权所属CP
     */
    private String copyrightCpid;

    /**
     * 主关键字
     */
    private String primaryKeywords;

    /**
     * 副关键字
     */
    private String keywords;

    /**
     * 收费类型。1:免费；2：收费
     */
    private String feeType;

    /**
     * 生成时间
     */
    private String createTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 服务类型。1：点播；2：直播；3:下载；4：点播+下载；7：精简编码；
     */
    private String contentType;

    /**
     * 内容类别。1：视频；2：音频；3：图文
     */
    private String category;

    /**
     * 描述媒资为剧集或单集
     */
    private String formType;

    /**
     * 标签
     */
    private String labels;

    /**
     * 操作员ID
     */
    private String creatorId;

    /**
     * 是否支持直播回放
     */
    private String directrecFlag;

    /**
     * 媒体文件清晰度。1：标清；2：高清；3：全高清
     */
    private String mediaLevel;

    /**
     * 内容作者
     */
    private String author;

    /**
     * 剧集的总集数
     */
    private String serialCount;

    /**
     * 单集在剧集中的序号
     */
    private String serialSequence;

    /**
     * 剧集ID
     */
    private String serialContentID;

    /**
     * 最后修改时间
     */
    private String lastModifytime;

    /**
     * 水印
     */
    private String waterMask;

    /**
     * 辅助分类信息
     */
    private String assist;

    /**
     * 内容以及分类ID
     */
    private String displayType;

    /**
     * 内容一级分类名称
     */
    private String displayName;

    /**
     * 自定义内容标识
     */
    private String udid;

    /**
     * 二级分类信息
     */
    private List<SecondClassify> secondClassifys;

    /**
     * 媒体文件信息
     */
    private List<MediaFile> mediaFiles;

    /**
     * 图片路径信息
     */
    private List<DisplayFile> displayFile;

    /**
     * 播控状态 0：未播控 1：播控通过 2：播控拒绝
     */
    private String bcStatus;

    /**
     * 操作员
     */
    private String bcPerson;

    /**
     * 播控时间
     */
    private String bcTime;

    /**
     * 播控拒绝原因
     */
    private String bcRefuseReason;

    /**
     * 发布状态
     */
    private String pubStatus;

    /**
     * 撤回状态10：待发布 11：发布中 12：发布成功 13：发布失败 20:待撤回 21:撤回中 22:撤回成功 23:撤回失败 30:无发布规则
     */
    private String repealStatus;

    public String getBcStatus()
    {
        return bcStatus;
    }

    public void setBcStatus(String bcStatus)
    {
        this.bcStatus = bcStatus;
    }

    public String getBcPerson()
    {
        return bcPerson;
    }

    public void setBcPerson(String bcPerson)
    {
        this.bcPerson = bcPerson;
    }

    public String getBcTime()
    {
        return bcTime;
    }

    public void setBcTime(String bcTime)
    {
        this.bcTime = bcTime;
    }

    public String getBcRefuseReason()
    {
        return bcRefuseReason;
    }

    public void setBcRefuseReason(String bcRefuseReason)
    {
        this.bcRefuseReason = bcRefuseReason;
    }

    public String getPubStatus()
    {
        return pubStatus;
    }

    public void setPubStatus(String pubStatus)
    {
        this.pubStatus = pubStatus;
    }

    public String getRepealStatus()
    {
        return repealStatus;
    }

    public void setRepealStatus(String repealStatus)
    {
        this.repealStatus = repealStatus;
    }

    public List<DisplayFile> getDisplayFile()
    {
        return displayFile;
    }

    public void setDisplayFile(List<DisplayFile> displayFile)
    {
        this.displayFile = displayFile;
    }

    public String getPrdContId()
    {
        return prdContId;
    }

    public void setPrdContId(String prdContId)
    {
        this.prdContId = prdContId;
    }

    public String getPrdInfoId()
    {
        return prdInfoId;
    }

    public void setPrdInfoId(String prdInfoId)
    {
        this.prdInfoId = prdInfoId;
    }

    public String getPrdInfoName()
    {
        return prdInfoName;
    }

    public void setPrdInfoName(String prdInfoName)
    {
        this.prdInfoName = prdInfoName;
    }

    public String getContentId()
    {
        return contentId;
    }

    public void setContentId(String contentId)
    {
        this.contentId = contentId;
    }

    public String getAssetId()
    {
        return assetId;
    }

    public void setAssetId(String assetId)
    {
        this.assetId = assetId;
    }

    public String getContentName()
    {
        return contentName;
    }

    public void setContentName(String contentName)
    {
        this.contentName = contentName;
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public String getCduration()
    {
        return cduration;
    }

    public void setCduration(String cduration)
    {
        this.cduration = cduration;
    }

    public String getBcId()
    {
        return bcId;
    }

    public void setBcId(String bcId)
    {
        this.bcId = bcId;
    }

    public String getCpId()
    {
        return cpId;
    }

    public void setCpId(String cpId)
    {
        this.cpId = cpId;
    }

    public String getNcpId()
    {
        return ncpId;
    }

    public void setNcpId(String ncpId)
    {
        this.ncpId = ncpId;
    }

    public String getCopyrightCpid()
    {
        return copyrightCpid;
    }

    public void setCopyrightCpid(String copyrightCpid)
    {
        this.copyrightCpid = copyrightCpid;
    }

    public String getPrimaryKeywords()
    {
        return primaryKeywords;
    }

    public void setPrimaryKeywords(String primaryKeywords)
    {
        this.primaryKeywords = primaryKeywords;
    }

    public String getKeywords()
    {
        return keywords;
    }

    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }

    public String getFeeType()
    {
        return feeType;
    }

    public void setFeeType(String feeType)
    {
        this.feeType = feeType;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getFormType()
    {
        return formType;
    }

    public void setFormType(String formType)
    {
        this.formType = formType;
    }

    public String getLabels()
    {
        return labels;
    }

    public void setLabels(String labels)
    {
        this.labels = labels;
    }

    public String getCreatorId()
    {
        return creatorId;
    }

    public void setCreatorId(String creatorId)
    {
        this.creatorId = creatorId;
    }

    public String getDirectrecFlag()
    {
        return directrecFlag;
    }

    public void setDirectrecFlag(String directrecFlag)
    {
        this.directrecFlag = directrecFlag;
    }

    public String getMediaLevel()
    {
        return mediaLevel;
    }

    public void setMediaLevel(String mediaLevel)
    {
        this.mediaLevel = mediaLevel;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getSerialCount()
    {
        return serialCount;
    }

    public void setSerialCount(String serialCount)
    {
        this.serialCount = serialCount;
    }

    public String getSerialSequence()
    {
        return serialSequence;
    }

    public void setSerialSequence(String serialSequence)
    {
        this.serialSequence = serialSequence;
    }

    public String getSerialContentID()
    {
        return serialContentID;
    }

    public void setSerialContentID(String serialContentID)
    {
        this.serialContentID = serialContentID;
    }

    public String getLastModifytime()
    {
        return lastModifytime;
    }

    public void setLastModifytime(String lastModifytime)
    {
        this.lastModifytime = lastModifytime;
    }

    public String getWaterMask()
    {
        return waterMask;
    }

    public void setWaterMask(String waterMask)
    {
        this.waterMask = waterMask;
    }

    public String getAssist()
    {
        return assist;
    }

    public void setAssist(String assist)
    {
        this.assist = assist;
    }

    public String getDisplayType()
    {
        return displayType;
    }

    public void setDisplayType(String displayType)
    {
        this.displayType = displayType;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getUdid()
    {
        return udid;
    }

    public void setUdid(String udid)
    {
        this.udid = udid;
    }

    public List<SecondClassify> getSecondClassifys()
    {
        return secondClassifys;
    }

    public void setSecondClassifys(List<SecondClassify> secondClassifys)
    {
        this.secondClassifys = secondClassifys;
    }

    public List<MediaFile> getMediaFiles()
    {
        return mediaFiles;
    }

    public void setMediaFiles(List<MediaFile> mediaFiles)
    {
        this.mediaFiles = mediaFiles;
    }

}
