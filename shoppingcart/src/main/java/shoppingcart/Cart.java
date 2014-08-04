package shoppingcart;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/4/14
 */
public interface Cart {
    void addToCart(int productCode, int quantity);

    Double calculateTotalPrice();

    int getItemsSize();

    Integer getProductCode(int i);

    void updateItem(int productCode, int quantity);
}
