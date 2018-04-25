package fdse.microservice.domain;

public class GetOrderByIdInfo {

    private String orderId;

    public GetOrderByIdInfo() {
        //Default Constructor
    }

    public GetOrderByIdInfo(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
