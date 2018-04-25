package cancel.domain;

public class AsyncSendToCancelOrderInfo {

    private String orderId;

    private String loginToken;

    public AsyncSendToCancelOrderInfo() {
        //do nothing
    }

    public AsyncSendToCancelOrderInfo(String orderId, String loginToken) {
        this.orderId = orderId;
        this.loginToken = loginToken;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }
}
