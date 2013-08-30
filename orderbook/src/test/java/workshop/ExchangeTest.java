package workshop;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
public class ExchangeTest {

    @Test
    public void shouldPlaceAndReturnId() throws Exception {

        StockExchange exchange = new StockExchange();
        Integer buyOrder = exchange.place("buy", 1);
        assertFalse(exchange.isOrderFilled(buyOrder));
        assertNotNull(buyOrder);
        Integer sellOrder = exchange.place("sell", 0);
        assertNotEquals(sellOrder, buyOrder);
        assertTrue(exchange.isOrderFilled(buyOrder));
        assertTrue(exchange.isOrderFilled(sellOrder));
    }

}
