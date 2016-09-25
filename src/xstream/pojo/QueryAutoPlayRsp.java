package xstream.pojo;

public class QueryAutoPlayRsp
{

    /**
     * 请求受理ID
     */
    private String processId;

    /**
     * 返回码
     */
    private String resCode;

    /**
     * 返回消息
     */
    private String resMessage;

    /**
     * 业务处理消息
     */
    private ResBusiness resBusiness;

    public String getProcessId()
    {
        return processId;
    }

    public void setProcessId(String processId)
    {
        this.processId = processId;
    }

    public String getResCode()
    {
        return resCode;
    }

    public void setResCode(String resCode)
    {
        this.resCode = resCode;
    }

    public String getResMessage()
    {
        return resMessage;
    }

    public void setResMessage(String resMessage)
    {
        this.resMessage = resMessage;
    }

    public ResBusiness getResBusiness()
    {
        return resBusiness;
    }

    public void setResBusiness(ResBusiness resBusiness)
    {
        this.resBusiness = resBusiness;
    }

}
