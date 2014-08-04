package shoppingcart;

import java.util.*;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/1/14
 */
public class SimpleCart implements Cart {

    private Map<Integer, CartItem> itemsMap = new LinkedHashMap<Integer, CartItem>();
    private PriceService priceService;

    public SimpleCart() {
    }

    @Override
    public void addToCart(int productCode, int quantityToAdd) {
        CartItem item = itemsMap.get(Integer.valueOf(productCode));
        if(item != null) {
            item.setQuantity(item.getQuantity() + quantityToAdd);
        } else {
            CartItem newItem = new CartItem(productCode, quantityToAdd);
            itemsMap.put(Integer.valueOf(productCode), newItem);
        }
    }

    @Override
    public Double calculateTotalPrice() {

        double sum = 0d;
        for (Iterator<Map.Entry<Integer, CartItem>> iterator = itemsMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Integer, CartItem> itemEntry = iterator.next();
            CartItem cartItem = itemEntry.getValue();
            sum += priceService.findPrice(cartItem.getProductCode()) * cartItem.getQuantity();
        }

        return sum;
    }

    public void setPriceService(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public int getItemsSize() {
        return itemsMap.size();
    }

    @Override
    public Integer getProductCode(int itemsIndex) {
        int i = 0;
        if(itemsIndex >= itemsMap.size()) {
            throw new IllegalStateException("No cart item with index "+itemsIndex);
        }
        Integer productCode = null;
        for (Iterator<Integer> iterator = itemsMap.keySet().iterator(); iterator.hasNext(); ) {
            Integer nextCode = iterator.next();
            if(i == itemsIndex) {
                productCode = nextCode;
                break;
            }
            i++;
        }

        return productCode;
    }

    @Override
    public void updateItem(int productCode, int quantity) {
        CartItem item = itemsMap.get(Integer.valueOf(productCode));
        if(item != null) {
            item.setQuantity(quantity);
            if(quantity == 0) {
                itemsMap.remove(Integer.valueOf(productCode));
            }
        }
    }

}
