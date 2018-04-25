package inside_payment.domain;

public class DrawbackAndCancel {

    private String userId;

    private String money;

    private String orderId;

    private String loginToken;

    public DrawbackAndCancel() {
        //do nothing
    }

    public DrawbackAndCancel(String userId, String money, String orderId, String loginToken) {
        this.userId = userId;
        this.money = money;
        this.orderId = orderId;
        this.loginToken = loginToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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
