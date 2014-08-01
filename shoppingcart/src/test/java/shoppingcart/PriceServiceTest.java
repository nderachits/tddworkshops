package shoppingcart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/1/14
 */
public class PriceServiceTest {
    @Test
    public void shouldReturnSavedPrice() throws Exception {
        PriceService priceService = new PriceService();
        priceService.setPrice(Integer.valueOf(42), 49.5d);

        assertEquals(Double.valueOf(49.5), priceService.findPrice(42));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPriceNotFound() throws Exception {
        PriceService priceService = new PriceService();
        priceService.findPrice(42);
    }

    @Test
    public void shouldStoreAndRetrievePricesForManyProducts() throws Exception {
        PriceService priceService = new PriceService();
        priceService.setPrice(Integer.valueOf(42), 49.5d);
        priceService.setPrice(Integer.valueOf(1), 19.0d);

        assertEquals(Double.valueOf(49.5), priceService.findPrice(42));
        assertEquals(Double.valueOf(19.0), priceService.findPrice(1));

    }
}
