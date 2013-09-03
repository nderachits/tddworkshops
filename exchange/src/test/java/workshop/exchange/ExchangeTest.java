package workshop.exchange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import workshop.exchange.exceptions.OrderNotFoundException;
import static workshop.exchange.OrderType.*;
import static workshop.exchange.OrderState.*;

public class ExchangeTest {

	private Exchange exchange;

	@Before
	public void setUp() {
		exchange = new Exchange();
	}

	@Test
	public void shouldReturnPlacedStateForNewOrder() {

		Integer id = placeOrderWithType(BUY);
		assertEquals(PLACED, exchange.getOrderState(id));

	}

	@Test(expected = OrderNotFoundException.class)
	public void shouldThrowWhenOrderNotFound() {
		exchange.getOrderState(-1);
	}

	@Test
	public void shouldFillOrder() {
		Integer buyOrder = placeOrderWithType(BUY);
		Integer sellOrder = placeOrderWithType(SELL);
		
		assertNotEquals(buyOrder, sellOrder);
		assertEquals(FILLED, exchange.getOrderState(buyOrder));
		assertEquals(FILLED, exchange.getOrderState(sellOrder));
	}

	private Integer placeOrderWithType(OrderType type) {
		return exchange.placeOrder(type, 100.0, 1);
	}

}
