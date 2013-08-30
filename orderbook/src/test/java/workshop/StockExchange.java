package workshop;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
class StockExchange {

    private int nextOrderId = 1;

    List<Order> orders = new ArrayList<Order>();

    public Integer place(String buyOrSell, int amount) {
        Order order = new Order(nextOrderId++, buyOrSell, amount);
        orders.add(order);
        fillOrders();
        return order.getId();
    }

    private void fillOrders() {
        for (int i = 0; i < orders.size() - 1; i++) {
            Order order1 = orders.get(i);
            if(!order1.getOrderState().equals("placed")) {
                continue;
            }
            for (int j = i + 1; j < orders.size(); j++) {
                Order order2 = orders.get(j);
                if(!order2.getOrderState().equals("placed")) {
                    continue;
                }
                if( !order1.getBuyOrSell().equals(order2.getBuyOrSell()) ||
                        order1.getAmount() == order2.getAmount()) {
                    matchOrders(order1, order2);
                }
            }
        }
    }

    private void matchOrders(Order order1, Order order2) {
        order1.setOrderState("filled");
        order2.setOrderState("filled");
    }

    public boolean isOrderFilled(Integer orderId) {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order.getOrderState().equals("filled");
            }
        }
        return false;
    }
}