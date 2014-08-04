package shoppingcart;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/4/14
 */
public interface Cart {
    void addToCart(int productId, int quantity);

    Double calculateTotalPrice();

    int getItemsSize();

    int getProductCode(int i);
}
