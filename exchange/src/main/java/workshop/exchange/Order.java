package workshop.exchange;

class Order {
	private final double price;
	private final int amount;
	private OrderState orderState;

	public Order(double price, int amount) {
		this.price = price;
		this.amount = amount;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getAmount() {
		return amount;
	}
}
