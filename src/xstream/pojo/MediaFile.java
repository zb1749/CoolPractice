package xstream.pojo;

public class MediaFile
{
    /**
     * 媒体资源ID
     */
    private String mediaFileId;

    /**
     * 资源名称
     */
    private String mediaFileName;

    /**
     * 码率
     */
    private String mediaCodeRate;

    /**
     * 资源类型
     */
    private String mediaType;

    /**
     * 分辨率
     */
    private String mediaResolution;

    /**
     * 媒体文件路径
     */
    private String mediaFilePreviewPath;

    /**
     * 类型编号等级
     */
    private String mediaUsageCode;

    public String getMediaFileId()
    {
        return mediaFileId;
    }

    public void setMediaFileId(String mediaFileId)
    {
        this.mediaFileId = mediaFileId;
    }

    public String getMediaFileName()
    {
        return mediaFileName;
    }

    public void setMediaFileName(String mediaFileName)
    {
        this.mediaFileName = mediaFileName;
    }

    public String getMediaCodeRate()
    {
        return mediaCodeRate;
    }

    public void setMediaCodeRate(String mediaCodeRate)
    {
        this.mediaCodeRate = mediaCodeRate;
    }

    public String getMediaType()
    {
        return mediaType;
    }

    public void setMediaType(String mediaType)
    {
        this.mediaType = mediaType;
    }

    public String getMediaResolution()
    {
        return mediaResolution;
    }

    public void setMediaResolution(String mediaResolution)
    {
        this.mediaResolution = mediaResolution;
    }

    public String getMediaFilePreviewPath()
    {
        return mediaFilePreviewPath;
    }

    public void setMediaFilePreviewPath(String mediaFilePreviewPath)
    {
        this.mediaFilePreviewPath = mediaFilePreviewPath;
    }

    public String getMediaUsageCode()
    {
        return mediaUsageCode;
    }

    public void setMediaUsageCode(String mediaUsageCode)
    {
        this.mediaUsageCode = mediaUsageCode;
    }

}
