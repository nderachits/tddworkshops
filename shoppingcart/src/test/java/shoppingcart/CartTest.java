package shoppingcart;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/1/14
 */
public class CartTest {
    @Test
    public void productIdShouldBeAddedToCart() throws Exception {
        Cart cart = new SimpleCart();
        cart.addToCart(42, 1);
        assertEquals(1, cart.getItemsSize());
        List<CartItem> items = cart.getItemsCopy();
        assertEquals(42, items.get(0).getProductCode());
    }

    @Test
    public void copySizeShouldBeZeroForNewCart() throws Exception {
        Cart cart = new SimpleCart();
        List<CartItem> items = cart.getItemsCopy();
        assertEquals(0, items.size());
        assertEquals(0, cart.getItemsSize());
    }

    @Test
    public void twoProductsShouldBeAddedToCart() throws Exception {
        Cart cart = new SimpleCart();
        cart.addToCart(1, 1);
        cart.addToCart(42, 1);
        List<CartItem> items = cart.getItemsCopy();
        assertEquals(2, items.size());
        assertEquals(1, items.get(0).getProductCode());
        assertEquals(42, items.get(1).getProductCode());
    }

    @Test
    public void newCartShouldBeEmpty() throws Exception {
        Cart cart = new SimpleCart();
        assertEquals(0, cart.getItemsSize());
    }

    @Test
    public void shouldReturnPriceOfCart() throws Exception {

        SimpleCart cart = new SimpleCart();
        cart.setPriceService(ObjectMother.createPriceService());

        cart.addToCart(1, 1);
        cart.addToCart(42, 1);
        assertEquals(Double.valueOf(68.5d), cart.calculateTotalPrice());
    }

    @Test
    public void productWithQuantityShouldBeCountedCorrectly() throws Exception {
        SimpleCart cart = new SimpleCart();
        cart.setPriceService(ObjectMother.createPriceService());

        cart.addToCart(42, 2);
        assertEquals(Double.valueOf(49.5 * 2), cart.calculateTotalPrice());
    }

    @Test
    public void productItemShouldBeUpdated() throws Exception {
        SimpleCart cart = new SimpleCart();
        cart.setPriceService(ObjectMother.createPriceService());
        cart.addToCart(1, 1);
        cart.addToCart(42, 4);
        cart.updateItem(42, 3);
        assertEquals(Double.valueOf(49.5 * 3 + 19.0), cart.calculateTotalPrice());
        assertEquals(2, cart.getItemsSize());
    }

    @Test
    public void itemShouldBeDeletedWhenQuantitySetToZero() throws Exception {
        SimpleCart cart = new SimpleCart();
        cart.setPriceService(ObjectMother.createPriceService());
        cart.addToCart(42, 4);
        cart.addToCart(1, 1);
        cart.updateItem(42, 0);
        assertEquals(1, cart.getItemsSize());
        assertEquals(Double.valueOf(19.0d), cart.calculateTotalPrice());
    }

    @Test
    public void quantityShouldBeIncreasedWhenAddingAlreadyPlacedProduct() throws Exception {
        SimpleCart cart = new SimpleCart();
        cart.setPriceService(ObjectMother.createPriceService());
        cart.addToCart(42, 2);
        cart.addToCart(42, 1);
        assertEquals(Double.valueOf(49.5d * 3), cart.calculateTotalPrice());
        assertEquals(1, cart.getItemsSize());
    }

    @Test
    public void shouldReturnCopyOfProductsWithQuantity() throws Exception {
        SimpleCart cart = ObjectMother.createCart();
        cart.addToCart(1, 2);
        cart.addToCart(42, 1);
        List<CartItem> items = cart.getItemsCopy();
        assertEquals(2, items.size());
        assertEquals(1, items.get(0).getProductCode());
        assertEquals(2, items.get(0).getQuantity());
        assertEquals(42, items.get(1).getProductCode());
        assertEquals(1, items.get(1).getQuantity());
    }

    @Test
    public void changingItemsCopyShouldNotChangeOrigin() throws Exception {
        SimpleCart cart = ObjectMother.createCart();
        cart.addToCart(1, 2);
        List<CartItem> itemsToChange = cart.getItemsCopy();
        itemsToChange.get(0).setQuantity(3);
        List<CartItem> items = cart.getItemsCopy();
        assertEquals(2, items.get(0).getQuantity());
    }

    @Test
    public void bundlePromotionShouldBeAppliedOnce() throws Exception {
        SimpleCart cart = ObjectMother.createCart();
        cart.setPromotionService(ObjectMother.createPromotionService());
        cart.addToCart(1, 1);
        cart.addToCart(42, 1);
        assertEquals(2, cart.getItemsSize());
        assertEquals(Double.valueOf(60d), cart.calculateTotalPrice());
    }

    @Test
    public void giftShouldBeAmongProductsWhenGiftPromotionApplied() throws Exception {
        OrderThresholdFreeGift promotion = new OrderThresholdFreeGift(200, 1);
        SimpleCart cart = ObjectMother.createCart();
        PromotionService promotions = new PromotionService();
        promotions.addPromotion(promotion);
        cart.setPromotionService(promotions);
        cart.addToCart(42,10);
        assertEquals(2, cart.getItemsSize());
        assertTrue(cart.getCartItem(1) != null);
    }

    @Test
    public void shouldRemoveGiftWhenCartIsNotApplicableAnymore() throws Exception {

        OrderThresholdFreeGift promotion = new OrderThresholdFreeGift(200, 1);
        SimpleCart cart = ObjectMother.createCart();
        PromotionService promotions = new PromotionService();
        promotions.addPromotion(promotion);
        cart.setPromotionService(promotions);
        cart.addToCart(42,10);
        cart.calculateTotalPrice();
        cart.updateItem(42, 1);
        assertEquals(1, cart.getItemsSize());
    }

    @Test
    public void shouldRemoveDiscountWhenPromotionIsNotApplicable() throws Exception {
        SimpleCart cart = ObjectMother.createCart();
        cart.setPromotionService(ObjectMother.createPromotionService());
        cart.addToCart(1, 1);
        cart.addToCart(42, 1);
        cart.calculateTotalPrice();
        cart.updateItem(42, 0);
        assertEquals(1, cart.getItemsSize());
        assertEquals(Double.valueOf(19.0d), cart.calculateTotalPrice());

    }
}
