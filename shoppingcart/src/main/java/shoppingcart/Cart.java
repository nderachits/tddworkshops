package shoppingcart;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/1/14
 */
public class Cart {

    private List<Integer> products = new ArrayList();
    private PriceService priceService;

    public Cart() {
    }

    public void addProductId(int productId) {
        products.add(productId);
    }


    public Integer[] getProducts() {
        return products.toArray(new Integer[0]);
    }

    public Double calculateTotalPrice() {
        Double sum = 0d;
        for (int i = 0; i < products.size(); i++) {
            Integer productId = products.get(i);
            sum += priceService.findPrice(productId);
        }
        return sum;
    }

    public void setPriceService(PriceService priceService) {
        this.priceService = priceService;
    }

    public void addProduct(int productId, int quantity) {
        for(int i = 0; i< quantity; i++) {
            products.add(productId);
        }
    }
}
