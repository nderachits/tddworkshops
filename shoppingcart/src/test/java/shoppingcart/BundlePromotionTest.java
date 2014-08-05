package shoppingcart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/5/14
 */
public class BundlePromotionTest {
    @Test
    public void promotionCaseShouldBeDetected() throws Exception {
        BundlePromotion promotion = new BundlePromotion(60d, 1, 42);
        SimpleCart cart = ObjectMother.createCart();
        cart.addToCart(1,1);
        cart.addToCart(42,1);
        assertEquals(true, promotion.isApplicable(cart));
    }

    @Test
    public void promotionShouldNotBeAppliedWhenProductMissed() throws Exception {
        BundlePromotion promotion = new BundlePromotion(60d, 1, 42);
        SimpleCart cart = ObjectMother.createCart();
        cart.addToCart(1,1);
        cart.addToCart(12,1);
        assertEquals(false, promotion.isApplicable(cart));
    }

    @Test
    public void shouldReturnPromotionAdjustment() throws Exception {
        BundlePromotion promotion = new BundlePromotion(60d, 1, 42);
        SimpleCart cart = ObjectMother.createCart();
        cart.addToCart(1,1);
        cart.addToCart(42,1);
        assertEquals(-8.5d, promotion.getAdjustment(cart), 0.0001);
    }
}
