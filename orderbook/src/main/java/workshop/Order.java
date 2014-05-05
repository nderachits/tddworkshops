package workshop;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
class Order {
    private Integer id;
    private Direction buyOrSell;
    private int amount;
    private OrderState orderState;


    public Order(Integer id, Direction buyOrSell, int amount) {
        this.id = id;
        this.buyOrSell = buyOrSell;
        this.amount = amount;
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
}