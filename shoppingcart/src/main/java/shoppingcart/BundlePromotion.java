package shoppingcart;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/5/14
 */
public class BundlePromotion implements Promotion {
    final private double promotionPrice;
    final private int[] productsInPromotion;

    public BundlePromotion(double promotionPrice, int... products) {
        this.promotionPrice = promotionPrice;
        this.productsInPromotion = products;
        if(products.length == 0) {
            throw new IllegalArgumentException("Bundle promotion must be applied to at least one product");
        }
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
        double adjustmentForOne = promotionPrice - ordinaryPrice;

        int timesPromotionApplied = Integer.MAX_VALUE;
        for (int i = 0; i < productsInPromotion.length; i++) {
            int productCode = productsInPromotion[i];
            CartItem cartItem = cart.getCartItem(productCode);
            timesPromotionApplied = Math.min(timesPromotionApplied, cartItem.getQuantity());
        }

        return adjustmentForOne * timesPromotionApplied;
    }

    @Override
    public void apply(Cart cart) {
        cart.setAdjustment(getAdjustment(cart));
    }
}
