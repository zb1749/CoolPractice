package collection.different.pojo;


public class ProductCyclePojo {
	private String productId;// 产品编号
	private String cycleNum;// 周期数
	private String fee;// 周期总费用
	private String supportPayTypes;// 支付方式---0,点播业务代码支付1,包月业务代码支付2,书券支付3,权益支付4,appstore支付5,绑定移动号码支付6,ESOP支付
	private String appStoreProductId;// appstore产品ID
	private String appStoreApplicationId;// appstore应用ID
	private String clientPrompt;// 客户端优惠订购提示语
	private String autoRenewalFlag;// 用户是否可以选择自动续订: 1-是 0-否

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCycleNum() {
		return cycleNum;
	}

	public void setCycleNum(String cycleNum) {
		this.cycleNum = cycleNum;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getSupportPayTypes() {
		return supportPayTypes;
	}

	public void setSupportPayTypes(String supportPayTypes) {
		this.supportPayTypes = supportPayTypes;
	}

	public String getAppStoreProductId() {
		return appStoreProductId;
	}

	public void setAppStoreProductId(String appStoreProductId) {
		this.appStoreProductId = appStoreProductId;
	}

	public String getAppStoreApplicationId() {
		return appStoreApplicationId;
	}

	public void setAppStoreApplicationId(String appStoreApplicationId) {
		this.appStoreApplicationId = appStoreApplicationId;
	}

	public String getClientPrompt() {
		return clientPrompt;
	}

	public void setClientPrompt(String clientPrompt) {
		this.clientPrompt = clientPrompt;
	}

	public String getAutoRenewalFlag() {
		return autoRenewalFlag;
	}

	public void setAutoRenewalFlag(String autoRenewalFlag) {
		this.autoRenewalFlag = autoRenewalFlag;
	}

	public String toString() {
		return "[ productId: " + productId + " cycleNum: " + cycleNum
				+ " fee: " + fee + " supportPayTypes: " + supportPayTypes
				+ " appStoreProductId: " + appStoreProductId
				+ " appStoreApplicationId: " + appStoreApplicationId
				+ " clientPrompt: " + clientPrompt + " autoRenewalFlag: "
				+ autoRenewalFlag + " ]";
	}


	public boolean equalPojo(ProductCyclePojo obj) {
		return isEq(this.getProductId(), obj.getProductId())
				&& isEq(this.getCycleNum(), obj.getCycleNum())
				&& isEq(this.getFee(), obj.getFee())
				&& isEq(this.getSupportPayTypes(), obj.getSupportPayTypes())
				&& isEq(this.getAppStoreProductId(), obj.getAppStoreProductId())
				&& isEq(this.getAppStoreApplicationId(), obj.getAppStoreApplicationId())
				&& isEq(this.getClientPrompt(), obj.getClientPrompt())
				&& isEq(this.getAutoRenewalFlag(), obj.getAutoRenewalFlag());
	}

	/**
	 * 判断两个字符串是否相等
	 */
	private boolean isEq(String str, String other) {
		if (str == null) {
			return other == null;
		}
		return str.equals(other);
	}

}
