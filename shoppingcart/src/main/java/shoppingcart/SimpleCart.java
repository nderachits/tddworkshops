package shoppingcart;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/1/14
 */
public class SimpleCart implements Cart {

    private List<CartItem> items = new ArrayList<CartItem>();
    private PriceService priceService;

    public SimpleCart() {
    }

    @Override
    public void addToCart(int productCode, int quantityToAdd) {
        int productItemFound = findCartItem(productCode);
        if(productItemFound != -1) {
            CartItem item = items.get(productItemFound);
            item.setQuantity(item.getQuantity() + quantityToAdd);
        } else {
            items.add(new CartItem(productCode, quantityToAdd));
        }
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

    @Override
    public void updateItem(int productCode, int quantity) {
        int index = findCartItem(productCode);
        if(index >= 0) {
            items.get(index).setQuantity(quantity);
            if(quantity == 0) {
                items.remove(index);
            }
        }
    }

    private int findCartItem(int productCode) {
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            CartItem cartItem = items.get(i);
            if(cartItem.getProductCode() == productCode) {
                index = i;
                break;
            }
        }
        return index;
    }
}
