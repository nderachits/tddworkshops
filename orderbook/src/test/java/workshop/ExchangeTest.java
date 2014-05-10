package workshop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
public class ExchangeTest {

    public static final double DELTA = 0.0001;
    private StockExchange exchange;

    @Before
    public void setUp() throws Exception {
        exchange = new SimpleStockExchange();
    }

    @Test
    public void shouldPlaceAndReturnId() throws Exception {

        Integer buyOrder = exchange.place(Direction.BUY, 1, 5.0);
        OrderView buyOrderView = exchange.getOrderStateObject(buyOrder);
        assertEquals(OrderState.PLACED, buyOrderView.getOrderState());
        assertNotNull(buyOrder);
        assertEquals(buyOrder.intValue(), buyOrderView.getOrderId());

        Integer sellOrder = exchange.place(Direction.SELL, 1, 5.0);
        assertNotEquals(sellOrder, buyOrder);
        assertEquals(OrderState.FILLED, exchange.getOrderStateObject(buyOrder).getOrderState());
        assertEquals(OrderState.FILLED, exchange.getOrderStateObject(sellOrder).getOrderState());
    }

    @Test(expected = OrderNotFoundException.class)
    public void shouldThrowOrderNotFoundWhenOrderNotPlaced() throws Exception {
        exchange.getOrderStateObject(-1);
    }

    @Test
    public void shouldBeFilledInOrder() throws Exception {

        exchange.place(Direction.BUY, 1, 5.0);
        Integer secondBuyId = exchange.place(Direction.BUY, 1, 5.0);
        exchange.place(Direction.SELL, 1, 5.0);
        assertEquals("Second Buy order should be left not filled", OrderState.PLACED, exchange.getOrderStateObject(secondBuyId).getOrderState());
    }

    @Test
    public void shouldNotBeFilledTheSameOrderTwice() throws Exception {
        exchange.place(Direction.BUY, 1, 5.0);
        exchange.place(Direction.SELL, 1, 5.0);
        Integer secondSellId = exchange.place(Direction.SELL, 1, 5.0);
        assertEquals("The second Sell order should be left unfilled", OrderState.PLACED, exchange.getOrderStateObject(secondSellId).getOrderState());
    }

    @Test
    public void shouldNotMatchWithBuyPriceLessThanSellPrice() throws Exception {

        Integer buyOrderId = exchange.place(Direction.SELL, 1, 6.0);
        Integer sellOrderId = exchange.place(Direction.BUY, 1, 5.0);

        OrderView buyOrder =  exchange.getOrderStateObject(buyOrderId);
        OrderView sellOrder =  exchange.getOrderStateObject(sellOrderId);

        assertEquals(OrderState.PLACED, buyOrder.getOrderState());
        assertEquals(OrderState.PLACED, sellOrder.getOrderState());

    }

    @Test
    public void shouldNotMatchWithBuyPriceLessThanSellPriceAnotherOrder() throws Exception {

        Integer sellOrderId = exchange.place(Direction.BUY, 1, 5.0);
        Integer buyOrderId = exchange.place(Direction.SELL, 1, 6.0);

        OrderView sellOrder =  exchange.getOrderStateObject(sellOrderId);
        OrderView buyOrder =  exchange.getOrderStateObject(buyOrderId);

        assertEquals(OrderState.PLACED, buyOrder.getOrderState());
        assertEquals(OrderState.PLACED, sellOrder.getOrderState());

    }

    @Test
    public void shouldFillOrdersWithLowestPrice() throws Exception {
        Integer sellOrderId = exchange.place(Direction.BUY, 1, 6.0);
        Integer buyOrderId = exchange.place(Direction.SELL, 1, 5.0);

        OrderView sellOrder =  exchange.getOrderStateObject(sellOrderId);
        OrderView buyOrder =  exchange.getOrderStateObject(buyOrderId);

        assertEquals(OrderState.FILLED, buyOrder.getOrderState());
        assertEquals(OrderState.FILLED, sellOrder.getOrderState());
        assertEquals(5.0, sellOrder.getFillPrice(), DELTA);
        assertEquals(5.0, buyOrder.getFillPrice(), DELTA);

    }

    @Test
    public void shouldFillOrdersWithLowestPriceAnotherOrder() throws Exception {
        Integer buyOrderId = exchange.place(Direction.SELL, 1, 5.0);
        Integer sellOrderId = exchange.place(Direction.BUY, 1, 6.0);

        OrderView sellOrder =  exchange.getOrderStateObject(sellOrderId);
        OrderView buyOrder =  exchange.getOrderStateObject(buyOrderId);

        assertEquals(OrderState.FILLED, buyOrder.getOrderState());
        assertEquals(OrderState.FILLED, sellOrder.getOrderState());
        assertEquals(5.0, sellOrder.getFillPrice(), DELTA);
        assertEquals(5.0, buyOrder.getFillPrice(), DELTA);

    }

    @Test
    public void shouldNotMatchOrdersWithDifferentAmount() throws Exception {
        Integer buyOrderId = exchange.place(Direction.SELL, 2, 5.0);
        Integer sellOrderId = exchange.place(Direction.BUY, 1, 6.0);

        OrderView sellOrder =  exchange.getOrderStateObject(sellOrderId);
        OrderView buyOrder =  exchange.getOrderStateObject(buyOrderId);

        assertEquals(OrderState.PLACED, buyOrder.getOrderState());
        assertEquals(OrderState.PLACED, sellOrder.getOrderState());

    }
}
