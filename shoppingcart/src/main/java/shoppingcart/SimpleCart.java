package shoppingcart;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/1/14
 */
public class SimpleCart implements Cart {

    private List<CartItem> items = new ArrayList();
    private PriceService priceService;

    public SimpleCart() {
    }

    @Override
    public void addToCart(int productId, int quantity) {
        items.add(new CartItem(productId, quantity));
    }

    @Override
    public Double calculateTotalPrice() {
        Double sum = 0d;
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            sum += priceService.findPrice(item.getProductCode()) * item.getQuantity();
        }

        return sum;
    }

    public void setPriceService(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public int getItemsSize() {
        return items.size();
    }

    @Override
    public int getProductCode(int i) {
        return items.get(i).getProductCode();
    }
}
