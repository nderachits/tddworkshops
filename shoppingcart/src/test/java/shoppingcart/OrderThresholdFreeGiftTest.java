package shoppingcart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/6/14
 */
public class OrderThresholdFreeGiftTest {
    @Test
    public void shouldAddFreeProductWhenPriceIsHigher200() throws Exception {
        OrderThresholdFreeGift promotion = new OrderThresholdFreeGift(200, 1);
        SimpleCart cart = ObjectMother.createCart();
        cart.addToCart(42,10);
        assertEquals(true, promotion.isApplicable(cart));
        assertEquals(0d, promotion.getAdjustment(cart), 0.0001d);
    }

}
