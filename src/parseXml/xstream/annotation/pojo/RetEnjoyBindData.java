package parseXml.xstream.annotation.pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class RetEnjoyBindData {
	@XStreamAlias("mobile")
	private String mobile;
	@XStreamAlias("cardId")
	private String cardId;
	@XStreamAlias("retCode")
	private String retCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

}
