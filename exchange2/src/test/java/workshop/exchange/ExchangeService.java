package workshop.exchange;

import java.util.ArrayList;
import java.util.List;

public class ExchangeService {

	private List<Order> orders = new ArrayList<>();
	private int nextOrderId = 0;
	
	public int placeOrder(int amount, float price, Direction direction) {
		Order order = new Order(amount, price, direction);
		order.setId(nextOrderId);
		orders.add(order);
		nextOrderId++;
		tryToMatchOrder(order);
		return order.getId();
	}

	private void tryToMatchOrder(Order order) {		
		for(Order candidate : orders){
			if (isAMatch(order, candidate)){
				order.setOrderState(OrderState.FILLED);
				candidate.setOrderState(OrderState.FILLED);
			}
		}
	}

	private boolean isAMatch(Order order, Order candidate) {
		if(order.getDirection()==candidate.getDirection()) {
			return false;
		}
		if (order.getOrderState()!=OrderState.PLACED || 
				candidate.getOrderState()!=OrderState.PLACED){
			return false;
		}
		if(order.getPrice()==candidate.getPrice()){
			if(order.getAmount()==candidate.getAmount()){
				return true;
			}
		}
		return false;
	}

	public OrderState getOrderState(int orderId) {
		return getOrderById(orderId).getOrderState();
	}
	
	private Order getOrderById(int orderId) {
		for(Order order: orders) {
			if(order.getId() == orderId) {
				return order;
			}
		}
		throw new IllegalArgumentException("Order id not found");
	}
}
