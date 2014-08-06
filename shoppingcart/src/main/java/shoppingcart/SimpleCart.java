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
    private CartItem gift;
    private boolean promotionsNeedRecalculate = true;

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
        promotionsNeedRecalculate = true;
    }

    @Override
    public Double calculateTotalPrice() {
        calculatePromotions();

        double sum = 0d;
        for (Iterator<Map.Entry<Integer, CartItem>> iterator = itemsMap.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Integer, CartItem> itemEntry = iterator.next();
            CartItem cartItem = itemEntry.getValue();
            sum += priceService.findPrice(cartItem.getProductCode()) * cartItem.getQuantity();
        }
        sum += adjustment;
        return sum;
    }

    private void calculatePromotions() {
        if(promotionsNeedRecalculate) {
            if(promotionService != null) {
                promotionService.applyToCart(this);
            }
        }
        promotionsNeedRecalculate = false;
    }

    public void setPriceService(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public int getItemsSize() {
        calculatePromotions();

        int size = itemsMap.size();
        if( gift != null ){
            size++;
        }
        return size;
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
        promotionsNeedRecalculate = true;
    }

    @Override
    public List<CartItem> getItemsCopyBeforePromotionsApplied() {
        List<CartItem> copy = new ArrayList<>();
        for (Iterator<CartItem> iterator = itemsMap.values().iterator(); iterator.hasNext(); ) {
            CartItem next = iterator.next();
            copy.add(new CartItem(next.getProductCode(), next.getQuantity()));
        }
        return copy;
    }

    @Override
    public List<CartItem> getItemsCopy() {
        calculatePromotions();
        List<CartItem> items = getItemsCopyBeforePromotionsApplied();
        if(gift != null) {
            items.add(gift);
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

    @Override
    public CartItem getCartItem(int productCode) {
        int quantity = 0;
        if(gift!= null && gift.getProductCode() == productCode) {
            quantity += gift.getQuantity();
        }
        CartItem item = itemsMap.get(productCode);
        if(item != null) {
            quantity += item.getQuantity();
        }

        if(quantity == 0) {
            return null;
        } else {
            return new CartItem(productCode, quantity);
        }
    }

    @Override
    public void applyGift(int giftCode) {
        this.gift = new CartItem(giftCode, 1);
    }

    @Override
    public void removeGift(int giftCode) {
        this.gift = null;
    }
}
