package workshop;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
class Order {
    private final double price;
    private double fillPrice;
    private Integer id;
    private final Direction buyOrSell;
    private int amount;
    private OrderState orderState;


    public Order(Integer id, Direction buyOrSell, int amount, double price) {
        this.id = id;
        this.buyOrSell = buyOrSell;
        this.amount = amount;
        this.price = price;
        this.orderState = OrderState.PLACED;
    }

    public Direction getBuyOrSell() {
        return buyOrSell;
    }

    public int getAmount() {
        return amount;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Integer getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    double getFillPrice() {
        return fillPrice;
    }

    void setFillPrice(double fillPrice) {
        this.fillPrice = fillPrice;
    }
}