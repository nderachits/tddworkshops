package performance;

import org.junit.Test;
import workshop.Direction;
import workshop.SimpleStockExchange;
import workshop.StockExchange;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
public class PerformanceTest {
    @Test
    public void testExchangePerformanceOnRandomData() throws Exception {

        int numOfOrders = 1000;

        long startTime = System.currentTimeMillis();

        StockExchange exchange = new SimpleStockExchange();

        for (int i = 0; i < numOfOrders; i++) {
             exchange.place(Direction.BUY, 1);
        }

        for (int i = 0; i < numOfOrders; i++) {
            exchange.place(Direction.SELL, 1);
        }

        float deltaTimeBroot = (System.currentTimeMillis() - startTime) / 1000f;
        System.out.println("perfomance test(repeated "+numOfOrders+"): " +
                "SimpleStockExchange: "+deltaTimeBroot);

    }
}
