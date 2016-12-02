package parseXml.xstream.annotation.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Response")
public class BatchBindCardResponse {
	@XStreamAlias("bindTime")
	private String bindTime;
	@XStreamAlias("retData")
	private RetDataPojo retData;

	public String getBindTime() {
		return bindTime;
	}

	public void setBindTime(String bindTime) {
		this.bindTime = bindTime;
	}

	public RetDataPojo getRetData() {
		return retData;
	}

	public void setRetData(RetDataPojo retData) {
		this.retData = retData;
	}

}
