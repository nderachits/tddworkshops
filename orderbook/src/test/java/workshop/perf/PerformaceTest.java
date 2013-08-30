package workshop.perf;

import org.junit.Test;
import workshop.SimpleStockExchange;
import workshop.StockExchange;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
public class PerformaceTest {
    @Test
    public void testExchangePerformanceOnRandomData() throws Exception {

        int numOfOrders = 10000;

        long startTime = System.currentTimeMillis();

        StockExchange exchange = new SimpleStockExchange();

        for (int i = 0; i < numOfOrders; i++) {
             exchange.place("buy", 1);
        }

        for (int i = 0; i < numOfOrders; i++) {
            exchange.place("sell", 1);
        }

        float deltaTimeBroot = (System.currentTimeMillis() - startTime) / 1000f;
        System.out.println("perfomance test(repeated "+numOfOrders+"): " +
                "SimpleStockExchange: "+deltaTimeBroot);

    }
}
