package workshop;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
public interface StockExchange {

    Integer place(Direction buyOrSell, int amount, double price);

    OrderView getOrderStateObject(int orderId);
}
