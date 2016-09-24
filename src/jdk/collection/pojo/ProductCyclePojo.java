package jdk.collection.pojo;

import lombok.Getter;
import lombok.Setter;

public class ProductCyclePojo {
	@Setter
	@Getter
	private String productId;// 产品编号
	@Setter
	@Getter
	private String cycleNum;// 周期数
	@Setter
	@Getter
	private String fee;// 周期总费用
	@Setter
	@Getter
	private String supportPayTypes;// 支付方式---0,点播业务代码支付1,包月业务代码支付2,书券支付3,权益支付4,appstore支付5,绑定移动号码支付6,ESOP支付
	@Setter
	@Getter
	private String appStoreProductId;// appstore产品ID
	@Setter
	@Getter
	private String appStoreApplicationId;// appstore应用ID
	@Setter
	@Getter
	private String clientPrompt;// 客户端优惠订购提示语
	@Setter
	@Getter
	private String autoRenewalFlag;// 用户是否可以选择自动续订: 1-是 0-否

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
