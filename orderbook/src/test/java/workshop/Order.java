package workshop;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
class Order {
    private Integer id;
    private String buyOrSell;
    private int amount;
    private String orderState;


    public Order(Integer id, String buyOrSell, int amount) {
        this.id = id;
        this.buyOrSell = buyOrSell;
        this.amount = amount;
        this.orderState = "placed";
    }

    String getBuyOrSell() {
        return buyOrSell;
    }

    int getAmount() {
        return amount;
    }

    String getOrderState() {
        return orderState;
    }

    void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    Integer getId() {
        return id;
    }
}