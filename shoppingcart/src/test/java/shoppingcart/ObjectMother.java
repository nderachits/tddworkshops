package shoppingcart;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/5/14
 */
public class ObjectMother {

    public static PriceService createPriceService() {
        PriceService priceService = new PriceService();
        priceService.setPrice(Integer.valueOf(42), 49.5d);
        priceService.setPrice(Integer.valueOf(1), 19.0d);
        return priceService;
    }

    public static SimpleCart createCart() {
        SimpleCart cart = new SimpleCart();
        cart.setPriceService(ObjectMother.createPriceService());
        return cart;
    }

    public static PromotionService createPromotionService() {
        PromotionService promotions = new PromotionService();
        BundlePromotion bundle = new BundlePromotion(60d, 1, 42);
        promotions.addPromotion(bundle);
        return promotions;
    }

}
