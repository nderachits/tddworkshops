package shoppingcart;

import java.util.List;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/4/14
 */
public interface Cart {
    void addToCart(int productCode, int quantity);

    Double calculateTotalPrice();

    int getItemsSize();

    void updateItem(int productCode, int quantity);

    List<CartItem> getItemsCopy();

    List<CartItem> getItemsCopyBeforePromotionsApplied();

    PriceService getPriceService();

    void setAdjustment(double adjustment);

    CartItem getCartItem(int productCode);

    void applyGift(int giftCode);

    void removeGift(int giftCode);
}
