package shoppingcart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        CartItem[] items = cart.getItemsCopy();
        assertEquals(42, items[0].getProductCode());
    }

    @Test
    public void copySizeShouldBeZeroForNewCart() throws Exception {
        Cart cart = new SimpleCart();
        CartItem[] items = cart.getItemsCopy();
        assertEquals(0, items.length);
        assertEquals(0, cart.getItemsSize());
    }

    @Test
    public void twoProductsShouldBeAddedToCart() throws Exception {
        Cart cart = new SimpleCart();
        cart.addToCart(1, 1);
        cart.addToCart(42, 1);
        CartItem[] items = cart.getItemsCopy();
        assertEquals(2, items.length);
        assertEquals(1, items[0].getProductCode());
        assertEquals(42, items[1].getProductCode());
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
        CartItem[] items = cart.getItemsCopy();
        assertEquals(2, items.length);
        assertEquals(1, items[0].getProductCode());
        assertEquals(2, items[0].getQuantity());
        assertEquals(42, items[1].getProductCode());
        assertEquals(1, items[1].getQuantity());
    }

    @Test
    public void changingItemsCopyShouldNotChangeOrigin() throws Exception {
        SimpleCart cart = ObjectMother.createCart();
        cart.addToCart(1, 2);
        CartItem[] itemsToChange = cart.getItemsCopy();
        itemsToChange[0].setQuantity(3);
        CartItem[] items = cart.getItemsCopy();
        assertEquals(2, items[0].getQuantity());
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
}
