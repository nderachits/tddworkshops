package shoppingcart;

import java.util.List;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/6/14
 */
public class OrderThresholdFreeGift implements Promotion {
    private double cartThreshold;
    private int giftCode;

    public OrderThresholdFreeGift(double cartThreshold, int giftCode) {
        this.cartThreshold = cartThreshold;
        this.giftCode = giftCode;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        double sum = 0d;
        List<CartItem> items = cart.getItemsCopyBeforePromotionsApplied();
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            sum += cart.getPriceService().findPrice(item.getProductCode()) * item.getQuantity();
        }
        if(sum >= cartThreshold) {
            return true;
        }
        return false;
    }

    @Override
    public double getAdjustment(Cart cart) {
        return 0;
    }

    @Override
    public void apply(Cart cart) {
        cart.applyGift(giftCode);
    }

    @Override
    public void cancel(Cart cart) {
        cart.removeGift(giftCode);
    }
}
