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

        Integer buyOrder = exchange.place(Direction.BUY, 1, 5.0);
        assertEquals(OrderState.PLACED, exchange.getOrderState(buyOrder));
        assertNotNull(buyOrder);
        Integer sellOrder = exchange.place(Direction.SELL, 1, 5.0);
        assertNotEquals(sellOrder, buyOrder);
        assertEquals(OrderState.FILLED, exchange.getOrderState(buyOrder));
        assertEquals(OrderState.FILLED, exchange.getOrderState(sellOrder));
    }

    @Test(expected = OrderNotFoundException.class)
    public void shouldThrowOrderNotFoundWhenOrderNotPlaced() throws Exception {
        exchange.getOrderState(-1);
    }

    @Test
    public void shouldBeFilledInOrder() throws Exception {

        exchange.place(Direction.BUY, 1, 5.0);
        Integer secondBuyId = exchange.place(Direction.BUY, 1, 5.0);
        exchange.place(Direction.SELL, 1, 5.0);
        assertEquals("Second Buy order should be left not filled", OrderState.PLACED, exchange.getOrderState(secondBuyId));
    }

    @Test
    public void shouldNotBeFilledTheSameOrderTwice() throws Exception {
        exchange.place(Direction.BUY, 1, 5.0);
        exchange.place(Direction.SELL, 1, 5.0);
        Integer secondSellId = exchange.place(Direction.SELL, 1, 5.0);
        assertEquals("The second Sell order should be left unfilled", OrderState.PLACED, exchange.getOrderState(secondSellId));
    }

    @Test
    public void shouldNotMatchWithBuyPriceLessThanSellPrice() throws Exception {

        Integer buyOrderId = exchange.place(Direction.SELL, 1, 6.0);
        Integer sellOrderId = exchange.place(Direction.BUY, 1, 5.0);

        assertEquals(OrderState.PLACED, exchange.getOrderState(buyOrderId));
        assertEquals(OrderState.PLACED, exchange.getOrderState(sellOrderId));

    }

    @Test
    public void shouldNotMatchWithBuyPriceLessThanSellPriceAnotherOrder() throws Exception {

        Integer sellOrderId = exchange.place(Direction.BUY, 1, 5.0);
        Integer buyOrderId = exchange.place(Direction.SELL, 1, 6.0);

        assertEquals(OrderState.PLACED, exchange.getOrderState(buyOrderId));
        assertEquals(OrderState.PLACED, exchange.getOrderState(sellOrderId));

    }
}
