package workshop.exchange;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExchangeServiceTest {
	
	private ExchangeService testedInstance = new ExchangeService();
	
	@Test
	public void shouldReturnPlacedStateAfterPlaceOrder(){
		int returnedId = testedInstance.placeOrder(10, 1f, Direction.BUY);
		OrderState actOrderState = testedInstance.getOrderState(returnedId);
		assertEquals(OrderState.PLACED, actOrderState);
	}
	
	@Test
	public void shouldReturnDifferentIdsForDifferentOrders(){
		int firstOrderId = testedInstance.placeOrder(10, 1f, Direction.BUY);
		int secondOrderId = testedInstance.placeOrder(10, 1f, Direction.BUY);
		assertFalse(firstOrderId==secondOrderId);
	}
	
	@Test
	public void shouldMatchOpositeOrders(){
		int buyOrderId = testedInstance.placeOrder(10, 1f, Direction.BUY);
		int sellOrderId = testedInstance.placeOrder(10, 1f, Direction.SELL);
		
		assertEquals(OrderState.FILLED, testedInstance.getOrderState(buyOrderId));
		assertEquals(OrderState.FILLED, testedInstance.getOrderState(sellOrderId));
	}
}
