package shoppingcart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/5/14
 */
public class PromotionServiceTest {

    @Test
    public void promotionsShouldBeApplied() throws Exception {
        PromotionService promotionService = new PromotionService();
        Promotion promotion = new PromotionStub(true, -5d);
        SimpleCart cart = new SimpleCart();
        promotionService.addPromotion(promotion);
        promotionService.applyToCart(cart);

        assertEquals(Double.valueOf(-5d), cart.calculateTotalPrice());
    }
}
