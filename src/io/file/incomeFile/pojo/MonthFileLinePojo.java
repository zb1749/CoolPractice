package io.file.incomeFile.pojo;

public class MonthFileLinePojo {
	private String operType;// 操作类型-W：全量
	private String operTime;// 操作时间-格式：YYYYMMDDHHMISS
	private String symbolType;// 标识类型-01 – 手机号
	private String symbolValue;// 标识值
	private String businessType;// 业务类型代码-81 – 咪咕业务
	private String corpCode;// 企业代码-数媒代码为698041
	private String contractId;// 合约id
	private String subscribeStatus;// 用户订购套餐状态-0：正常 1：暂停
	private String effectDate;// 业务生效时间-格式为：YYYYMMDD

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public String getSymbolType() {
		return symbolType;
	}

	public void setSymbolType(String symbolType) {
		this.symbolType = symbolType;
	}

	public String getSymbolValue() {
		return symbolValue;
	}

	public void setSymbolValue(String symbolValue) {
		this.symbolValue = symbolValue;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getSubscribeStatus() {
		return subscribeStatus;
	}

	public void setSubscribeStatus(String subscribeStatus) {
		this.subscribeStatus = subscribeStatus;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String toString() {
		return "MonthFileLinePojo{" +
				"operType='" + operType + '\'' +
				", operTime='" + operTime + '\'' +
				", symbolType='" + symbolType + '\'' +
				", symbolValue='" + symbolValue + '\'' +
				", businessType='" + businessType + '\'' +
				", corpCode='" + corpCode + '\'' +
				", contractId='" + contractId + '\'' +
				", subscribeStatus='" + subscribeStatus + '\'' +
				", effectDate='" + effectDate + '\'' +
				'}';
	}
}
