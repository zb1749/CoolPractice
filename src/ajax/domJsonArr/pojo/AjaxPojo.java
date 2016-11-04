package ajax.domJsonArr.pojo;

/**
 * Created by kevin on 2016/9/16.
 */
public class AjaxPojo {
    private String productId;// 产品编号
    private String cycleNum;// 周期数
    private String fee;// 周期总费用
    private String supportPayTypes;// 支付方式
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

    @Override
    public String toString() {
        return "AjaxPojo{" +
                "productId='" + productId + '\'' +
                ", cycleNum='" + cycleNum + '\'' +
                ", fee='" + fee + '\'' +
                ", supportPayTypes='" + supportPayTypes + '\'' +
                ", appStoreProductId='" + appStoreProductId + '\'' +
                ", appStoreApplicationId='" + appStoreApplicationId + '\'' +
                ", clientPrompt='" + clientPrompt + '\'' +
                ", autoRenewalFlag='" + autoRenewalFlag + '\'' +
                '}';
    }
}
