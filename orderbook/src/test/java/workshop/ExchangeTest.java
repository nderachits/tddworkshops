package workshop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
public class ExchangeTest {

    private StockExchange exchange;

    @Before
    public void setUp() throws Exception {
        exchange = new SimpleStockExchange();
    }

    @Test
    public void shouldPlaceAndReturnId() throws Exception {

        Integer buyOrder = exchange.place("buy", 1);
        assertEquals("placed", exchange.getOrderState(buyOrder));
        assertNotNull(buyOrder);
        Integer sellOrder = exchange.place("sell", 1);
        assertNotEquals(sellOrder, buyOrder);
        assertEquals("filled", exchange.getOrderState(buyOrder));
        assertEquals("filled", exchange.getOrderState(sellOrder));
    }

    @Test(expected = OrderNotFoundException.class)
    public void shouldThrowOrderNotFoundWhenOrderNotPlaced() throws Exception {
        exchange.getOrderState(-1);
    }

    @Test
    public void shouldBeFilledInOrder() throws Exception {

        exchange.place("buy", 1);
        Integer secondBuyId = exchange.place("buy", 1);
        exchange.place("sell", 1);
        assertEquals("Second Buy order should be left not filled", "placed", exchange.getOrderState(secondBuyId));
    }

    @Test
    public void shouldNotBeFilledTheSameOrderTwice() throws Exception {
        exchange.place("buy", 1);
        exchange.place("sell", 1);
        Integer secondSellId = exchange.place("sell", 1);
        assertEquals("The second Sell order should be left unfilled", "placed", exchange.getOrderState(secondSellId));
    }
}
