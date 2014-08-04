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
        assertEquals(42, cart.getProductCode(0));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowWhenRequestingNotExistingCartItem() throws Exception {
        Cart cart = new SimpleCart();
        cart.getProductCode(0);
    }

    @Test
    public void twoProductsShouldBeAddedToCart() throws Exception {
        Cart cart = new SimpleCart();
        cart.addToCart(1, 1);
        cart.addToCart(42, 1);
        assertEquals(2, cart.getItemsSize());
        assertEquals(1, cart.getProductCode(0));
        assertEquals(42, cart.getProductCode(1));
    }

    @Test
    public void newCartShouldBeEmpty() throws Exception {
        Cart cart = new SimpleCart();
        assertEquals(0, cart.getItemsSize());
    }

    @Test
    public void shouldReturnPriceOfCart() throws Exception {

        SimpleCart cart = new SimpleCart();
        cart.setPriceService(buildPriceService());

        cart.addToCart(1, 1);
        cart.addToCart(42, 1);
        assertEquals(Double.valueOf(68.5d), cart.calculateTotalPrice());
    }

    private PriceService buildPriceService() {
        PriceService priceService = new PriceService();
        priceService.setPrice(Integer.valueOf(42), 49.5d);
        priceService.setPrice(Integer.valueOf(1), 19.0d);
        return priceService;
    }

    @Test
    public void productWithQuantityShouldBeCountedCorrectly() throws Exception {
        SimpleCart cart = new SimpleCart();
        cart.setPriceService(buildPriceService());

        cart.addToCart(42, 2);
        assertEquals(Double.valueOf(49.5 * 2), cart.calculateTotalPrice());
    }

    @Test
    public void productItemShouldBeUpdated() throws Exception {
        SimpleCart cart = new SimpleCart();
        cart.setPriceService(buildPriceService());
        cart.addToCart(1, 1);
        cart.addToCart(42, 4);
        cart.updateItem(42, 3);
        assertEquals(Double.valueOf(49.5 * 3 + 19.0), cart.calculateTotalPrice());
        assertEquals(2, cart.getItemsSize());
    }

    @Test
    public void itemShouldBeDeletedWhenQuantitySetToZero() throws Exception {
        SimpleCart cart = new SimpleCart();
        cart.setPriceService(buildPriceService());
        cart.addToCart(42, 4);
        cart.addToCart(1, 1);
        cart.updateItem(42, 0);
        assertEquals(1, cart.getItemsSize());
        assertEquals(Double.valueOf(19.0d), cart.calculateTotalPrice());
    }

    @Test
    public void quantityShouldBeIncreasedWhenAddingAlreadyPlacedProduct() throws Exception {
        SimpleCart cart = new SimpleCart();
        cart.setPriceService(buildPriceService());
        cart.addToCart(42, 2);
        cart.addToCart(42, 1);
        assertEquals(Double.valueOf(49.5d * 3), cart.calculateTotalPrice());
        assertEquals(1, cart.getItemsSize());
    }
}
