package workshop;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
public class SimpleStockExchange implements StockExchange {

    private int nextOrderId = 1;

    List<Order> orders = new ArrayList<Order>();

    @Override
    public Integer place(Direction buyOrSell, int amount) {
        Order order = new Order(nextOrderId++, buyOrSell, amount);
        orders.add(order);
        tryToFillOrder(order);
        return order.getId();
    }

    private void tryToFillOrder(Order order) {
        for (Order candidate : orders) {

            if(isAMatch(order, candidate)) {
                matchOrders(order, candidate);
                break;
            }
        }
    }

    private boolean isAMatch(Order order, Order candidate) {
        if(order.getOrderState() != OrderState.PLACED ||
                candidate.getOrderState() != OrderState.PLACED) {
            return false;
        }

        if( !order.getBuyOrSell().equals(candidate.getBuyOrSell()) &&
                order.getAmount() == candidate.getAmount()) {
            return true;
        }

        return false;

    }

    private void matchOrders(Order order1, Order order2) {
        order1.setOrderState(OrderState.FILLED);
        order2.setOrderState(OrderState.FILLED);
    }

    @Override
    public OrderState getOrderState(Integer orderId) {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order.getOrderState();
            }
        }
        throw new OrderNotFoundException(orderId);
    }
}