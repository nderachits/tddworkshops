package workshop.exchange;

public class Order {

	private int id;
	private final int amount;
	private final float price;
	private final Direction direction;
	private OrderState orderState;

	public Order(int amount, float price, Direction direction) {
		this.amount = amount;
		this.price = price;
		this.direction = direction;
		this.orderState = OrderState.PLACED; 
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public float getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}

	public Direction getDirection() {
		return direction;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}
}
