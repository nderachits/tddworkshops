package shoppingcart;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/1/14
 */
public class PriceService {

    private Map<Integer, Double> prices;

    public PriceService() {
        prices = new HashMap<Integer, Double>();
    }

    public void setPrice(Integer productId, Double price) {
        prices.put(productId, price);
    }

    public Double findPrice(Integer productId) {
        Double price = prices.get(productId);
        if(price == null) {
            throw new IllegalArgumentException("Price not found for product id: "+productId);
        }
        return price;
    }
}
