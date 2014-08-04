package shoppingcart;

import java.util.*;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/1/14
 */
public class SimpleCart implements Cart {

    private List<CartItem> items = new ArrayList<CartItem>();
    private Map<Integer, CartItem> itemsMap = new LinkedHashMap<Integer, CartItem>();
    private PriceService priceService;

    public SimpleCart() {
    }

    @Override
    public void addToCart(int productCode, int quantityToAdd) {
        int productItemFound = findCartItem(productCode);
        CartItem cartItem = itemsMap.get(Integer.valueOf(productCode));
        if(productItemFound != -1) {
            CartItem item = items.get(productItemFound);
            if(item != cartItem) {
                throw new IllegalStateException("addToCart refactoring failed");
            }
            item.setQuantity(item.getQuantity() + quantityToAdd);
        } else {
            CartItem newItem = new CartItem(productCode, quantityToAdd);
            items.add(newItem);
            itemsMap.put(Integer.valueOf(productCode), newItem);
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
        if(itemsMap.size() != items.size()) {
            throw new IllegalStateException("getItemsSize refactoring");
        }
        return items.size();
    }

    @Override
    public int getProductCode(int itemsIndex) {
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

        if(productCode != items.get(itemsIndex).getProductCode()) {
            throw new IllegalStateException("getProductCode refactoring");
        }
        return items.get(itemsIndex).getProductCode();
    }

    @Override
    public void updateItem(int productCode, int quantity) {
        int index = findCartItem(productCode);
        CartItem cartItem = itemsMap.get(Integer.valueOf(productCode));
        if(index >= 0) {
            CartItem item = items.get(index);
            if(cartItem != item) {
                throw new IllegalStateException("updateItem refactoring");
            }
            item.setQuantity(quantity);
            if(quantity == 0) {
                items.remove(index);
                itemsMap.remove(Integer.valueOf(productCode));
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
