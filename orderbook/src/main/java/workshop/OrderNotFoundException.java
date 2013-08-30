package workshop;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/30/13
 */
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Integer orderId) {
        super("Order not found with id "+orderId);
    }

}
