package workshop;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
public interface StockExchange {

    Integer place(String buyOrSell, int amount);
    String getOrderState(Integer orderId);
}
