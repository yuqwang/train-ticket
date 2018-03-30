package preserve.domain;

public class OrderTicketsResult {

    private boolean status;

    private String message;

    private Order order;

    private int preserveNumber;

    public OrderTicketsResult(){
        //Default Constructor
    }

    public int getPreserveNumber() {
        return preserveNumber;
    }

    public void setPreserveNumber(int preserveNumber) {
        this.preserveNumber = preserveNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
