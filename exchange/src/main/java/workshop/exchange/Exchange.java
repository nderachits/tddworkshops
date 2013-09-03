package workshop.exchange;

import java.awt.RadialGradientPaint;
import java.util.HashMap;
import java.util.Map;

import workshop.exchange.exceptions.OrderNotFoundException;

public class Exchange {

	private int orderId;

	private Map<Integer, Order> ordersToBuy = new HashMap<>();
	private Map<Integer, Order> ordersToSell = new HashMap<>();

	public Integer placeOrder(OrderType orderType, double price, int amount) {
		int orderId = getNewOrderId();
		
		Map<Integer, Order> ordersToMatch = new HashMap<>();
		Order orderToPlace = createPlacedOrder(price, amount);
		
		if (orderType.equals(OrderType.BUY)) {
			ordersToBuy.put(orderId, orderToPlace);
			ordersToMatch = ordersToSell;	
		} else
		if (orderType.equals(OrderType.SELL)) {
			ordersToSell.put(orderId, orderToPlace);
			ordersToMatch = ordersToBuy;
		}
		
		matchOrder(orderToPlace, ordersToMatch);
		return orderId;
	}
	
	private void matchOrder(Order orderToPlace, Map<Integer, Order> ordersToMatch) {
		for (Order order : ordersToMatch.values()) {
			if (isOrdersMatched(orderToPlace, order)) {
				order.setOrderState(OrderState.FILLED);
				orderToPlace.setOrderState(OrderState.FILLED);
			}
		}
	}
	
	private boolean isOrdersMatched(Order target, Order source) {
		return (target.getAmount()== source.getAmount()) && (target.getPrice() == source.getPrice());
	}
	

	private Order createPlacedOrder(double price, int amount) {
		Order order = new Order(price, amount);
		order.setOrderState(OrderState.PLACED);
		return order;
	}

	public OrderState getOrderState(Integer orderId) {
		Order order = null;
		if (ordersToBuy.keySet().contains(orderId))
			order = ordersToBuy.get(orderId);
		else
		if (ordersToSell.keySet().contains(orderId))
			order = ordersToSell.get(orderId);
		
		if (order == null)
			throw new OrderNotFoundException("Cound not find order with id: " + orderId);
		return order.getOrderState();
	}

	private int getNewOrderId() {
		orderId++;
		return orderId;
	}

}
