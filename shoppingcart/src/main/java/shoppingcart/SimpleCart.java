package shoppingcart;

import java.util.*;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/1/14
 */
public class SimpleCart implements Cart {

    private LinkedHashMap<Integer, CartItem> itemsMap = new LinkedHashMap<Integer, CartItem>();
    private PriceService priceService;
    private PromotionService promotionService;
    private double adjustment = 0d;

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
        if(promotionService != null) {
            promotionService.applyToCart(this);
        }

        sum += adjustment;

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
    public void updateItem(int productCode, int quantity) {
        CartItem item = itemsMap.get(Integer.valueOf(productCode));
        if(item != null) {
            item.setQuantity(quantity);
            if(quantity == 0) {
                itemsMap.remove(Integer.valueOf(productCode));
            }
        }
    }

    @Override
    public CartItem[] getItemsCopy() {
        Collection<CartItem> values = itemsMap.values();
        CartItem[] items = new CartItem[values.size()];
        int i=0;
        for (Iterator<CartItem> iterator = values.iterator(); iterator.hasNext(); ) {
            CartItem item = iterator.next();
            try {
                items[i] = (CartItem) item.clone();
            } catch (CloneNotSupportedException e) {
                throw new IllegalStateException(e);
            }
            i++;
        }
        return items;
    }

    public void setPromotionService(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    public PriceService getPriceService() {
        return priceService;
    }

    @Override
    public void setAdjustment(double adjustment) {
        this.adjustment = adjustment;
    }
}
