package workshop;

/**
 * User: nike
 * Date: 5/10/14
 */
public class OrderView {

    private int orderId;

    private double fillPrice;
    private OrderState orderState;

    public OrderView(int orderId, OrderState orderState, double fillPrice) {
        this.orderId = orderId;
        this.orderState = orderState;
        this.fillPrice = fillPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getFillPrice() {
        return fillPrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }

}
