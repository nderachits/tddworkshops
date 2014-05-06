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
    public Integer place(Direction buyOrSell, int amount, double price) {
        Order order = new Order(nextOrderId++, buyOrSell, amount, price);
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

        if( order.getBuyOrSell().equals(candidate.getBuyOrSell())) {
            return false;
        }

        if( order.getAmount() != candidate.getAmount()) {
            return false;
        }

        Order sellOrder = candidate;
        Order buyOrder = order;
        if(sellOrder.getBuyOrSell() != Direction.SELL) {
            sellOrder = order;
            buyOrder = candidate;
        }
        if(sellOrder.getPrice() > buyOrder.getPrice()) {
            return false;
        }

        return true;

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