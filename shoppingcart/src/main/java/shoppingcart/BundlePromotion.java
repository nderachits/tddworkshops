package shoppingcart;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/5/14
 */
public class BundlePromotion implements Promotion {
    private double promotionPrice;
    private int[] productsInPromotion;

    public BundlePromotion(double promotionPrice, int... products) {
        this.promotionPrice = promotionPrice;
        this.productsInPromotion = products;
    }

    @Override
    public boolean isApplicable(Cart cart) {
        boolean allFound = true;
        CartItem[] items = cart.getItemsCopy();
        for (int i = 0; i < productsInPromotion.length; i++) {
            int promoProduct = productsInPromotion[i];
            boolean productFound = false;
            for (int j = 0; j < items.length; j++) {
                CartItem cartItem = items[j];
                if(cartItem.getProductCode() == promoProduct) {
                    productFound = true;
                }
            }
            if(productFound == false) {
                allFound = false;
            }
        }
        return allFound;
    }

    @Override
    public double getAdjustment(Cart cart) {
        PriceService priceService = cart.getPriceService();
        double ordinaryPrice = 0d;
        for (int i = 0; i < productsInPromotion.length; i++) {
            ordinaryPrice += priceService.findPrice(productsInPromotion[i]);
        }
        return promotionPrice - ordinaryPrice;
    }

    @Override
    public void apply(Cart cart) {
        cart.setAdjustment(getAdjustment(cart));
    }
}
